package br.com.softexpert.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import feign.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE, reason = "Servico para URL Pix esta indisponivel")
public class FeignException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private Response response;
}
