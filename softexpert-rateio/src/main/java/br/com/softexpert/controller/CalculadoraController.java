package br.com.softexpert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softexpert.controller.dto.RateioRequest;
import br.com.softexpert.controller.dto.RateioResponse;
import br.com.softexpert.domain.model.Rateio;
import br.com.softexpert.domain.service.CalculadoraService;

@RestController
@Validated
public class CalculadoraController implements CalculadoraAPI {

	@Autowired
	private CalculadoraService service;

	@Override
	@CrossOrigin
	@PostMapping(value = "/calculos")
	public ResponseEntity<RateioResponse> calcular(RateioRequest request, boolean gerarLinkPagamento) {
		Rateio rateio = service.processarRateio(
				request.getPedidoParaRateio(), 
				request.getChave(),
				gerarLinkPagamento);
		RateioResponse response = RateioResponse.builder().rateio(rateio).build();
		return ResponseEntity.ok(response);
	}

}
