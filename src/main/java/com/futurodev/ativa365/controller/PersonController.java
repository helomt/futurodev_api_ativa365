package com.futurodev.ativa365.controller;

import com.futurodev.ativa365.exceptions.*;
import com.futurodev.ativa365.model.transport.CreatePersonForm;
import com.futurodev.ativa365.model.transport.LoginForm;
import com.futurodev.ativa365.model.transport.PersonDTO;
import com.futurodev.ativa365.model.transport.TokenDTO;
import com.futurodev.ativa365.service.AuthenticationService;
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
    private final AuthenticationService authenticationService;

    public PersonController(PersonService personService, AuthenticationService authenticationService) {
        this.personService = personService;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody @Valid CreatePersonForm form,
                                                  UriComponentsBuilder uriComponentsBuilder)
            throws PersonEmailAlreadyExistsException, PersonCpfAlreadyExistsException, CepNotFoundException {
        PersonDTO response = this.personService.createPerson(form);
        URI uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginForm form){
        TokenDTO response = this.authenticationService.login(form);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<TokenDTO> refreshToken(@RequestHeader("Authorization") String refreshToken){
        TokenDTO response = this.authenticationService.refreshToken(refreshToken);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<PersonDTO>> listPaginatedPersons(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        Page<PersonDTO> response = this.personService.listPaginatedPerson(pageable);
        return response.hasContent() ? ResponseEntity.ok(response): ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonDTO> deletePerson(@PathVariable("id") Long id,
                                                  @AuthenticationPrincipal UserDetails userInSession)
            throws PersonNotFoundException, PersonToBeDeletedIsNotTheCurrentUserException {
        PersonDTO personInSession = this.personService.getUserInSession(userInSession.getUsername());
        if(Objects.equals(personInSession.id(), id)){
            this.personService.deletePerson(id);
            return ResponseEntity.noContent().build();
        } else{
            throw new PersonToBeDeletedIsNotTheCurrentUserException(id);
        }
    }

    @GetMapping("/authenticated")
    public ResponseEntity<String> isAuthenticated(){
        return ResponseEntity.ok("Is authenticated");
    }
}
