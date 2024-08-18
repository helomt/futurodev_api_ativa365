package com.futurodev.ativa365.model.transport;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Classe para armazenar as informações  de um endereço")
public record ViaCepDTO(
                        @Schema(description = "Cep do endereço", example = "88035-000")
                        String cep,
                        @Schema(description = "Logradouro do endereço", example = "Avenida A")
                        String logradouro,
                        @Schema(description = "Complemento do endereço", example = "Bloco A")
                        String complemento,
                        @Schema(description = "Bairro do endereço", example = "Bairro A")
                        String bairro,
                        @Schema(description = "Cidade do endereço", example = "Florianópolis")
                        String localidade,
                        @Schema(description = "Unidade Federativa (Estado) do endereço", example = "SC")
                        String uf) {
}
