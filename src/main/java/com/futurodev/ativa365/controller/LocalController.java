package com.futurodev.ativa365.controller;

import com.futurodev.ativa365.exceptions.PersonNotFoundException;
import com.futurodev.ativa365.model.transport.CreateLocalForm;
import com.futurodev.ativa365.model.transport.LocalDTO;
import com.futurodev.ativa365.service.LocalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/local")
public class LocalController {

    private final LocalService localService;


    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @PostMapping
    public ResponseEntity<LocalDTO> createLocal(@RequestBody @Valid CreateLocalForm form,
                                                UriComponentsBuilder uriComponentsBuilder,
                                                @AuthenticationPrincipal UserDetails userInSession) throws PersonNotFoundException {
        LocalDTO response =  this.localService.createLocal(form, userInSession);

        URI uri = uriComponentsBuilder.path("/local/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
