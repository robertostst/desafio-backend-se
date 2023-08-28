package br.com.softexpert.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softexpert.domain.exception.CalculoException;
import br.com.softexpert.domain.model.Item;
import br.com.softexpert.domain.model.Pedido;
import br.com.softexpert.domain.model.Rateio;
import ch.qos.logback.classic.Logger;

@Service
public class CalculadoraService {

	private static final Logger log = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(CalculadoraService.class);
	
	@Autowired
	private PixUrlService cobrancaService;

	public Rateio processarRateio(Pedido pedido, final String chave, boolean habilitarLinkPagamento) throws CalculoException {
		log.info("Iniciado o cálculo do rateio");
		
		Map<String, BigDecimal> valorPorPessoa = new HashMap<>();
		
		final BigDecimal valorTotal = pedido.getValorTotal();		
		final BigDecimal valorTotalAPagar = pedido.getValorTotalAPagar();	

		if (valorTotalAPagar.doubleValue() <= 0) {
			throw new CalculoException("O valor total a pagar é zero, você e seus amigos não precisam pagar nada.");
		}
		
		Map<String, BigDecimal> itensAgrupadoPorPessoa = agruparItensPorPessoa(pedido);		
		
		itensAgrupadoPorPessoa.entrySet().stream().forEach(a -> {
			BigDecimal valorAPagar = calcularValorAPagarPorPessoa(calcularPercentualPorPessoa(a.getValue(), valorTotal), valorTotalAPagar);
			BigDecimal valorFormatado = valorAPagar.setScale(2, RoundingMode.HALF_UP);
			valorPorPessoa.put(a.getKey(), valorFormatado);
		});			
		
		Rateio rateio = Rateio.builder()
				.valorTotalAPagar(pedido.getValorTotalAPagar())
				.valorAPagarPorPessoa(valorPorPessoa)				
				.build();

		if(habilitarLinkPagamento) {			
			rateio.setLinksPagamento(cobrancaService.gerarURLPix(valorPorPessoa, chave));
		}		

		log.info("Concluído o calculo do rateio com sucesso");
		
		return rateio;
	}	
	
	private Map<String, BigDecimal> agruparItensPorPessoa(Pedido pedido){
		return pedido.getItens().stream()
				.collect(Collectors.groupingBy(Item::getNomePessoa, Collectors.reducing(BigDecimal.ZERO, Item::getValor, BigDecimal::add)));
	}
	
	private BigDecimal calcularPercentualPorPessoa(BigDecimal valorPessoa, BigDecimal valorTotalItens) {
		return valorPessoa.multiply(BigDecimal.valueOf(100)).divide(valorTotalItens, 1, RoundingMode.HALF_UP);
	}
	
	private BigDecimal calcularValorAPagarPorPessoa(BigDecimal percentual, BigDecimal valorTotalAPagar) {
		return percentual.divide(BigDecimal.valueOf(100)).multiply(valorTotalAPagar);
	}
}
