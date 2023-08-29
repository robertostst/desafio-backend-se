package br.com.softexpert.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.softexpert.config.SwaggerConfig;
import br.com.softexpert.controller.dto.RateioRequest;
import br.com.softexpert.controller.dto.RateioResponse;
import io.swagger.annotations.Api;

@Api(tags = SwaggerConfig.TAG_CALCULADORA)
public interface CalculadoraAPI {

	public ResponseEntity<RateioResponse> calcular(@Valid @RequestBody RateioRequest request, @RequestParam(required = false, defaultValue = "false") boolean gerarLinkPagamento);
}
