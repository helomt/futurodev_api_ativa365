package com.futurodev.ativa365.client;

import com.futurodev.ativa365.model.transport.ViaCepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "via-cep-client", url = "viacep.com.br/ws")
public interface ViaCepClient {

    @GetMapping("/{cep}/json")
    ResponseEntity<ViaCepDTO> search(@PathVariable("cep") String cep);
}
