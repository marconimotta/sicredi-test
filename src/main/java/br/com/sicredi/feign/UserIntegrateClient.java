package br.com.sicredi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.sicredi.dto.UserIntegrateStatusDTO;
import br.com.sicredi.feign.config.DefaultClientConfig;

@FeignClient(value = "UserIntegrateClient", url = "${user-integrate.host}", configuration = DefaultClientConfig.class)
public interface UserIntegrateClient {

	@GetMapping("/users/{cpf}")
	UserIntegrateStatusDTO existsCpf(@PathVariable(value = "cpf") final String cpf);

}
