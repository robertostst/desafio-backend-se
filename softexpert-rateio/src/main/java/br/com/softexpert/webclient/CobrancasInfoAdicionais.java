package br.com.softexpert.webclient;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CobrancasInfoAdicionais {
	
	@JsonProperty("valor")
	private String valor = null;

	@JsonProperty("nome")
	private String nome = null;
}
