package br.com.softexpert.domain.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rateio {

	@ApiModelProperty(position = 1, notes = "Valor total a pagar pelo pedido", required = true)
	private BigDecimal valorTotalAPagar;

	@ApiModelProperty(position = 2, notes = "Nome e valor a pagar de cada pessoa", required = true)
	private Map<String, BigDecimal> valorAPagarPorPessoa;

	@ApiModelProperty(position = 3, notes = "Link para receber o pagamento. Cada pessoa tem um link diferente contendo o valor a pagar.", required = true)
	private List<LinkPagamento> linksPagamento;
}
