package com.futurodev.ativa365.controller;

import com.futurodev.ativa365.exceptions.PersonCpfAlreadyExistsException;
import com.futurodev.ativa365.exceptions.PersonEmailAlreadyExistsException;
import com.futurodev.ativa365.exceptions.PersonNotFoundException;
import com.futurodev.ativa365.exceptions.PersonToBeDeletedIsNotTheCurrentUser;
import com.futurodev.ativa365.model.transport.CreatePersonForm;
import com.futurodev.ativa365.model.transport.PersonDTO;
import com.futurodev.ativa365.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/usuario")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody @Valid CreatePersonForm form,
                                                  UriComponentsBuilder uriComponentsBuilder)
            throws PersonEmailAlreadyExistsException, PersonCpfAlreadyExistsException {
        PersonDTO response = this.personService.createPerson(form);
        URI uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<PersonDTO>> listPaginatedPersons(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        Page<PersonDTO> response = this.personService.listPaginatedPerson(pageable);
        return response.hasContent() ? ResponseEntity.ok(response): ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonDTO> deletePerson(@PathVariable("id") Long id,
                                                  @AuthenticationPrincipal UserDetails userInSession)
            throws PersonNotFoundException, PersonToBeDeletedIsNotTheCurrentUser {
        PersonDTO personInSession = this.personService.getUserInSession(userInSession.getUsername());
        if(Objects.equals(personInSession.id(), id)){
            this.personService.deletePerson(id);
            return ResponseEntity.noContent().build();
        } else{
            throw new PersonToBeDeletedIsNotTheCurrentUser(id);
        }

    }
}
