package com.futurodev.ativa365.controller;

import com.futurodev.ativa365.client.ViaCepClient;
import com.futurodev.ativa365.model.transport.ViaCepDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cep")
public class ViaCepController {

    private final ViaCepClient viaCepClient;

    public ViaCepController(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<ViaCepDTO> search(@PathVariable("cep") String cep){
        return this.viaCepClient.search(cep);
    }
}
