package br.com.softexpert.webclient.itau.invoker;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.softexpert.config.FeignConfig;
import br.com.softexpert.webclient.itau.model.TokenPostRequest;
import br.com.softexpert.webclient.itau.model.TokenPostResponse;

@FeignClient(name = "access-token-sandbox", url = "${pix.sandbox.url.token}", configuration = FeignConfig.class)
public interface TokenItauSandboxClient {

	@PostMapping
	ResponseEntity<TokenPostResponse> auth(@RequestBody TokenPostRequest request);
}
