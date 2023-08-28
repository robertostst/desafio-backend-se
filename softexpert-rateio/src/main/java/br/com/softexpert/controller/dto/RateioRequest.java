package br.com.softexpert.controller.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.softexpert.domain.model.Pedido;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RateioRequest {

	@ApiModelProperty(position = 1, notes = "Pedido que será dividido com outras pessoas", required = true)
	@Valid
	@NotNull
	private Pedido pedidoParaRatearComAmigos;

	@ApiModelProperty(position = 2, example = "robertocmfsp@gmail.com", notes = "Chave pix para receber pagamento", required = true)
	@Valid
	@NotNull
	private String chave;
}
