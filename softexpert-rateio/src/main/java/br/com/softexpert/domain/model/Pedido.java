package br.com.softexpert.domain.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
	
	@Valid
	@NotNull
	private List<Item> itens;
	
	@NotNull(message = "Informe o valor da taxa de entrega. Se não houver, informe 0.")
	private BigDecimal valorTaxaEntrega;

	@NotNull(message = "Informe o valor do desconto. Se não houver, informe 0.")
	private BigDecimal valorDesconto;

	@JsonIgnore
	public BigDecimal getValorTotal() {
		return this.itens.stream().map(Item::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@JsonIgnore
	public BigDecimal getValorTotalAPagar() {
		return BigDecimal.ZERO.add(this.getValorTotal()).add(valorTaxaEntrega).subtract(valorDesconto);
	}
}
