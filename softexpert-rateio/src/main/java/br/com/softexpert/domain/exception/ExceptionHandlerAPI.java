package br.com.softexpert.domain.exception;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAPI {

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ResponseBody
	public ExceptionResponse handle(Exception ex) throws IOException {
		ExceptionResponse response = ExceptionResponse.builder()
				.mensagem("Existem campos na sua requisição que estão inválidos. Corrija para que sua requisição seja processada.")				
				.httpStatus(HttpStatus.UNPROCESSABLE_ENTITY)
				.exception(MethodArgumentNotValidException.class.getCanonicalName())
				.build();	
		
		if (ex instanceof MethodArgumentNotValidException) {
			List<FieldError> fieldErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors();

			fieldErrors.stream().forEach(e -> {
				ExceptionResponse.Error error = response.new Error();

				error.setMensagem(e.getDefaultMessage());
				error.setCampo(e.getField());
				error.setValorRejeitado(Objects.nonNull(e.getRejectedValue()) ? e.getRejectedValue().toString() : null);

				response.getValidacoes().add(error);
			});

			
		}
		
		

		return response;
	}
	

}
