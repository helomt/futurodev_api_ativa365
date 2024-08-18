package com.futurodev.ativa365.controller;

import com.futurodev.ativa365.exceptions.*;
import com.futurodev.ativa365.model.transport.*;
import com.futurodev.ativa365.service.AuthenticationService;
import com.futurodev.ativa365.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Person Controller", description = "Controller para operações com o banco de dados para usuários")
public class PersonController {
    private final PersonService personService;
    private final AuthenticationService authenticationService;

    public PersonController(PersonService personService, AuthenticationService authenticationService) {
        this.personService = personService;
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Cadastrar um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CreatePersonForm.class))}),
            @ApiResponse(responseCode = "401", description = "Requisição não autorizada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))})
    })
    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody @Valid CreatePersonForm form,
                                                  UriComponentsBuilder uriComponentsBuilder)
            throws PersonEmailAlreadyExistsException, PersonCpfAlreadyExistsException, CepNotFoundException {
        PersonDTO response = this.personService.createPerson(form);
        URI uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }


    @Operation(summary = "Login de um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LoginForm.class))}),
            @ApiResponse(responseCode = "401", description = "Requisição não autorizada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "403", description = "Requisição proibida", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))})
    })
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginForm form){
        TokenDTO response = this.authenticationService.login(form);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Renovação de um usuário já logado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token renovado com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TokenDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Requisição não autorizada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "403", description = "Requisição proibida", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))})
    })
    @PostMapping("/refresh-token")
    public ResponseEntity<TokenDTO> refreshToken(@RequestHeader("Authorization") String refreshToken){
        TokenDTO response = this.authenticationService.refreshToken(refreshToken);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Listar usuários de maneira paginada", description = "Utilizado em ambiente de desenvolvimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuário carregada com sucesso", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Requisição não autorizada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "403", description = "Requisição proibida", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "404", description = "Lista de usuários não encontrada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))})
    })
    @GetMapping
    public ResponseEntity<Page<PersonDTO>> listPaginatedPersons(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        Page<PersonDTO> response = this.personService.listPaginatedPerson(pageable);
        return response.hasContent() ? ResponseEntity.ok(response): ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deleção de um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Requisição não autorizada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "403", description = "Requisição proibida", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))})
    })
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

    @Operation(summary = "Verificação de autenticação de um usuário", description = "Utilizado para teste")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Requisição não autorizada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "403", description = "Requisição proibida", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))})
    })
    @GetMapping("/authenticated")
    public ResponseEntity<String> isAuthenticated(){
        return ResponseEntity.ok("Is authenticated");
    }
}
