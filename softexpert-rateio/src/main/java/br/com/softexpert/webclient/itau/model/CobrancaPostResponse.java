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
public class CobrancaPostResponse {

	@JsonProperty("id_cobranca_imediata_pix")
	private String idCobrancaImediataPix;

	@JsonProperty("txid")
	private String txid;

	@JsonProperty("status")
	private String status;

	@JsonProperty("chave")
	private String chave;

	@JsonProperty("valor")
	private Valor valor;

	@JsonProperty("location")
	private String location;

	@JsonProperty("imagem_base64")
	private String qrcode;

}
