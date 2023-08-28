package br.com.softexpert.domain.exception;

import org.springframework.http.HttpStatus;

public class CalculoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CalculoException(String message) {
		super(message);
	}

	protected HttpStatus getStatusCode() {
		return HttpStatus.OK;
	}
}
