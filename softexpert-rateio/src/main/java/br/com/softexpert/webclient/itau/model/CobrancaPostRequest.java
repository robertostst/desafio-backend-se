package br.com.softexpert.webclient.itau.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CobrancaPostRequest {

	@JsonProperty("valor")
	private Valor valor;

	@JsonProperty("chave")
	private String chave;

	@JsonProperty("calendario")
	private Calendario calendario;

}
