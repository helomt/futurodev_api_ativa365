package com.futurodev.ativa365.controller;

import com.futurodev.ativa365.model.transport.CreatePersonForm;
import com.futurodev.ativa365.model.transport.PersonDTO;
import com.futurodev.ativa365.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody @Valid CreatePersonForm form, UriComponentsBuilder uriComponentsBuilder){
        PersonDTO response = this.personService.createPerson(form);
        URI uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
