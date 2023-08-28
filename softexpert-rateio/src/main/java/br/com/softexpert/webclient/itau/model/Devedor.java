package br.com.softexpert.webclient.itau.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Devedor {

	@JsonProperty("cpf")
	private String cpf;

	@JsonProperty("nome")
	private String nome;

	@JsonProperty("cnpj")
	private String cnpj;
}
