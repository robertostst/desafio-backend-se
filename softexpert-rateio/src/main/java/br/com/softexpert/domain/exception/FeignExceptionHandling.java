package br.com.softexpert.domain.exception;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignExceptionHandling implements ErrorDecoder {

	private static final Logger log = (ch.qos.logback.classic.Logger) LoggerFactory
			.getLogger(FeignExceptionHandling.class);

	@Override
	public Exception decode(String methodKey, Response response) {
		log.info("Servico para URL Pix esta indisponivel");
		log.warn(response.toString());
		return new FeignException(response);
	}
}
