package com.futurodev.ativa365.controller;

import com.futurodev.ativa365.exceptions.CepNotFoundException;
import com.futurodev.ativa365.exceptions.LocalNotFoundException;
import com.futurodev.ativa365.exceptions.PersonNotFoundException;
import com.futurodev.ativa365.model.transport.CreateLocalForm;
import com.futurodev.ativa365.model.transport.LocalDTO;
import com.futurodev.ativa365.model.transport.UpdateLocalForm;
import com.futurodev.ativa365.service.LocalService;
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

@RestController
@RequestMapping("/local")
@Tag(name = "Local Controller", description = "Controller para as operações com o banco de dados para os locais de atividade física")
public class LocalController {

    private final LocalService localService;


    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @Operation(summary = "Cadastrar um novo local")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Local criado com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CreateLocalForm.class))}),
            @ApiResponse(responseCode = "401", description = "Requisição não autorizada",content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))})
    })
    @PostMapping
    public ResponseEntity<LocalDTO> createLocal(@RequestBody @Valid CreateLocalForm form,
                                                UriComponentsBuilder uriComponentsBuilder,
                                                @AuthenticationPrincipal UserDetails userInSession) throws PersonNotFoundException, CepNotFoundException {
        LocalDTO response =  this.localService.createLocal(form, userInSession);
        URI uri = uriComponentsBuilder.path("/local/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }


    @Operation(summary = "Listar locais cadastrados por um usuário de maneira paginada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de locais carregada com sucesso",content = {@Content(mediaType = "application/json")} ),
            @ApiResponse(responseCode = "204", description = "Lista de locais carregada sem  conteúdo", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "401", description = "Requisição não autorizada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "403", description = "Requisição proibida", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "404", description = "Lista de locais não encontrada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))})
    })
    @GetMapping
    public ResponseEntity<Page<LocalDTO>> listPaginatedLocals(@PageableDefault(size = 10, sort = {"name"})Pageable pageable,
                                                              @AuthenticationPrincipal UserDetails userInSession) throws PersonNotFoundException {
       Page<LocalDTO> response = this.localService.listPaginatedLocals(pageable, userInSession);
       return response.hasContent() ? ResponseEntity.ok(response): ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar um local cadastrado por um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Local buscado com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LocalDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Requisição não autorizada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "403", description = "Requisição proibida", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "404", description = "Local não encontrado",content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<LocalDTO> getSingleLocal(@PathVariable("id") Long id,
                                                   @AuthenticationPrincipal UserDetails userInSession) throws LocalNotFoundException {
        LocalDTO response = this.localService.getSingleLocal(id, userInSession);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Deletar um local cadastrado por um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Local deletado com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LocalDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Requisição não autorizada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "403", description = "Requisição proibida", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "404", description = "Local não encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))})
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<LocalDTO> deleteLocal(@PathVariable("id") Long id,
                                                @AuthenticationPrincipal UserDetails userInSession) throws LocalNotFoundException {
        this.localService.deleteLocal(id, userInSession);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar um local cadastrado por um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Local atualizado com sucesso", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UpdateLocalForm.class))}),
            @ApiResponse(responseCode = "401", description = "Requisição não autorizada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "403", description = "Requisição proibida", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "404", description = "Local não encontrado", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))})
    })
    @PutMapping("/{id}")
    public ResponseEntity<LocalDTO> updateLocal(@PathVariable("id") Long id,
                                                @RequestBody @Valid UpdateLocalForm form,
                                                @AuthenticationPrincipal UserDetails userInSession) throws LocalNotFoundException, CepNotFoundException {
        LocalDTO response = this.localService.updateLocal(id, form, userInSession);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Listar todos os locais de maneira paginada", description = "Utilizado em ambiente de desenvolvimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de locais carregada com sucesso", content = {@Content(mediaType = "application/json")} ),
            @ApiResponse(responseCode = "204", description = "Lista de locais carregada sem  conteúdo", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "401", description = "Requisição não autorizada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "403", description = "Requisição proibida", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))}),
            @ApiResponse(responseCode = "404", description = "Lista de locais não encontrada", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetail.class))})
    })
    @GetMapping("/all")
    public ResponseEntity<Page<LocalDTO>> listPaginatedLocalsNoOwner(@PageableDefault(size = 10, sort = {"name"})Pageable pageable) {
        Page<LocalDTO> response = this.localService.listPaginatedLocalsNoOwner(pageable);
        return response.hasContent() ? ResponseEntity.ok(response): ResponseEntity.noContent().build();
    }


}
