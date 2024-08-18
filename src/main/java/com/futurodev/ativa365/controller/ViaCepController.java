package com.futurodev.ativa365.controller;

import com.futurodev.ativa365.client.ViaCepClient;
import com.futurodev.ativa365.model.transport.ViaCepDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cep")
@Tag(name = "ViaCep Controller", description = "Controller para operações com a API do ViaCep")
public class ViaCepController {

    private final ViaCepClient viaCepClient;

    public ViaCepController(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    @Operation(summary = "Consulta um endereço com base em um CEP")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "O endereço foi recuperado com sucesso",
            content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ViaCepDTO.class))}),
            @ApiResponse(responseCode = "404",
                    description = "O endereço para o CEP fornecido não foi encontrado",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class))}),



    })
    @GetMapping("/{cep}")
    public ResponseEntity<ViaCepDTO> search(@PathVariable("cep") String cep){
        return this.viaCepClient.search(cep);
    }
}
