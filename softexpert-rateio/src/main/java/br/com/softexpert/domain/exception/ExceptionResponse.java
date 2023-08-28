package br.com.softexpert.domain.exception;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

	@ApiModelProperty(position = 1, example = "Requisição inválida", notes = "Descrição do erro", allowEmptyValue = false, required = true)
	@JsonProperty(index = 0)
	private String mensagem;

	@ApiModelProperty(position = 2, example = "422", notes = "HTTP Status", required = true)
	@JsonProperty(value = "http-status", index = 1)
	private HttpStatus httpStatus;

	@ApiModelProperty(position = 3, notes = "Lista contendo a descrição de todas as validações da requisição", allowEmptyValue = false, required = true)
	@Builder.Default
	@JsonProperty(index = 2)
	private List<Error> validacoes = new LinkedList<Error>();

	@ApiModelProperty(position = 4, example = "[class java.lang.IllegalArgumentException]", notes = "Exception que ocorreu ao processar a requisição", required = false)
	@JsonProperty(index = 3)
	private String exception;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Data
	public class Error {

		@ApiModelProperty(position = 1)
		@JsonProperty(value = "validacao", index = 1)
		private String mensagem;

		@ApiModelProperty(position = 2)
		@JsonProperty(value = "campo-invalido", index = 2)
		private String campo;

		@ApiModelProperty(position = 3)
		@JsonProperty(value = "valor-rejeitado", index = 3)
		private String valorRejeitado;
	}
}
