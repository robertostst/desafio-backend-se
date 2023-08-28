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
public class QRcode {

	@JsonProperty("imagem_base64")
	private String imagemBase64;

	@JsonProperty("pix_link")
	private String pixLink;

	@JsonProperty("emv")
	private String emv;
}
