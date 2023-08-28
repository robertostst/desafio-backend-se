package br.com.softexpert.config;

import org.springframework.context.annotation.Bean;

import br.com.softexpert.domain.exception.FeignExceptionHandling;
import feign.Logger;
import feign.codec.ErrorDecoder;

//	@Configuration
public class FeignConfig {
	
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	@Bean
	ErrorDecoder errorDecoder() {
		return new FeignExceptionHandling();
	}
}
