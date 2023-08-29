package br.com.softexpert.domain.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.softexpert.domain.model.LinkPagamento;
import br.com.softexpert.webclient.itau.invoker.ItauSandboxClient;
import br.com.softexpert.webclient.itau.invoker.TokenItauSandboxClient;
import br.com.softexpert.webclient.itau.model.Calendario;
import br.com.softexpert.webclient.itau.model.CobrancaPostRequest;
import br.com.softexpert.webclient.itau.model.CobrancaPostResponse;
import br.com.softexpert.webclient.itau.model.TokenPostRequest;
import br.com.softexpert.webclient.itau.model.TokenPostResponse;
import br.com.softexpert.webclient.itau.model.Valor;
import ch.qos.logback.classic.Logger;


@Service
public class PixUrlService {
	
	private static final Logger log = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(PixUrlService.class);
	
	@Autowired
	private ItauSandboxClient itauSandbox;
	
	@Autowired
	private TokenItauSandboxClient tokenSandbox;	
	
	@Value("${pix.sandbox.x-itau-apikey:123}")
	private String xItauApikey;
	
	/*
	 * @Value("${pix.sandbox.x-itau-correlationID:123}") private String
	 * xItauCorrelationID;
	 */	
	@Value("${pix.sandbox.x-sandbox-token}")
	private String xSandboxToken;
	
	@Value("${pix.sandbox.client-id}")
	private String clientId;
	
	@Value("${pix.sandbox.client-secret}")
	private String clientSecret;	
	
	public List<LinkPagamento> gerarURLPix(Map<String, BigDecimal> map, final String chave) {		
			
		List<LinkPagamento> cobrancas = new ArrayList<>();
		
		log.info("x-itau-apikey: {}", xItauApikey);		
		
		TokenPostRequest tokenPostRequest = TokenPostRequest.builder().clientId(clientId).clientSecret(clientSecret).build();
		
		ResponseEntity<TokenPostResponse> accessToken = tokenSandbox.auth(tokenPostRequest);
		
		log.info("x-sandbox-token: {}", accessToken.getBody().getToken());
		
		map.entrySet().stream().forEach(a -> {
			CobrancaPostRequest request = CobrancaPostRequest.builder()
					.calendario(Calendario.builder().expiracao("").build())
					.valor(Valor.builder().original(a.getValue().toString()).build())
					.chave(chave)
					.build();
		
			ResponseEntity<CobrancaPostResponse> response = itauSandbox.cobranca_imediata_pix(xItauApikey, accessToken.getBody().getToken(), request);			
			
			LinkPagamento cobrancaPorPessoa = LinkPagamento.builder()
				.identificacaoDevedor(a.getKey())
				.valorAPagar(a.getValue())
				.pixLink(response.getBody().getLocation())
				.qrcode(response.getBody().getQrcode())
				.build();
			
			cobrancas.add(cobrancaPorPessoa);
		});		
		
		return cobrancas;
	}
}
