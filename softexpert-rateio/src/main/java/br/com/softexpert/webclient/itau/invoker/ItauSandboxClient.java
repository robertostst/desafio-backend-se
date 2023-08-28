package br.com.softexpert.webclient.itau.invoker;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.softexpert.config.FeignConfig;
import br.com.softexpert.webclient.itau.model.CobrancaPostRequest;
import br.com.softexpert.webclient.itau.model.CobrancaPostResponse;

@FeignClient(name = "api-pix-recebimentos-sandbox", url = "${pix.sandbox.url.cobranca-imediata-pix}", configuration = FeignConfig.class)
public interface ItauSandboxClient {

	@PostMapping
	ResponseEntity<CobrancaPostResponse> cobranca_imediata_pix(
			@RequestHeader("x-itau-apikey") String apikey,
			@RequestHeader("x-sandbox-token") String sandboxToken,
			@RequestBody CobrancaPostRequest request);
}
