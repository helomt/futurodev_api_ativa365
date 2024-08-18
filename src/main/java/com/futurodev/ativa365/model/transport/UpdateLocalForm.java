package com.futurodev.ativa365.model.transport;

import com.futurodev.ativa365.model.enums.ActivityEnum;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Classe para armazenar as informações de um local a ser atualizado")
public record UpdateLocalForm(
        @Schema(description = "Nome do Local", example = "Academia Fit")
        String name,
        @Schema(description = "Descrição do local de treino", example = "Local amplo")
                              String description,
        @Schema(description = "Cep do endereço da academia", example = "88035-000")
                              String cep,
        @Schema(description = "Número do endereço da academia", example = "100")
                              String number,
        @Schema(description = "Complemento do endereço da academia", example = "Bloco A")
                              String complement,
        @Schema(description = "Tipo de atividade usada na academia", example = "Musculação")
                              ActivityEnum activity) {
}
