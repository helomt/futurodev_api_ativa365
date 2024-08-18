package com.futurodev.ativa365.model.transport;

import com.futurodev.ativa365.model.enums.ActivityEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Classe para criar um novo local de treino")
public record CreateLocalForm(
            @Schema(description = "Nome do Local", example = "Academia Fit")
            @NotBlank String name,
                              @Schema(description = "Descrição do local de treino", example = "Local amplo")
                              @NotBlank String description,
                              @Schema(description = "Cep do endereço da academia", example = "88035-000")
                              @NotBlank  @Valid String cep,
                              @Schema(description = "Número do endereço da academia", example = "100")
                              String number,
                              @Schema(description = "Complemento do endereço da academia", example = "Bloco A")
                              String complement,
                              @Schema(description = "Tipo de atividade usada na academia", example = "Musculação")
                              @NotNull ActivityEnum activity) {
}
