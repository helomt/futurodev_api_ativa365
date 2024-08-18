package com.futurodev.ativa365.model.transport;

import com.futurodev.ativa365.model.Local;
import com.futurodev.ativa365.model.enums.ActivityEnum;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Classe para armazenar as informações de um local")
public record LocalDTO(
        @Schema(description = "Número de identificação do cadastro no banco", example = "1")
        Long id,
        @Schema(description = "Nome do Local", example = "Academia Fit")
                       String name,
        @Schema(description = "Descrição do local de treino", example = "Local amplo")
                       String description,
        @Schema(description = "Cep do endereço da academia", example = "88035-000")
                       String cep,
        @Schema(description = "Número do endereço da academia", example = "100")
                       String number,
        @Schema(description = "Cidade do endereço da academia", example = "Florianópolis")
                       String city,
        @Schema(description = "Rua do endereço da academia", example = "Avenida A")
                       String street,
        @Schema(description = "Estado do endereço da academia", example = "Santa Catarina")
                       String state,
        @Schema(description = "Complemento do endereço da academia", example = "Bloco A")
                       String complement,
        @Schema(description = "Tipo de atividade usada na academia", example = "Musculação")
                       ActivityEnum activity,
        @Schema(description = "Usuário que criou esse registro", example = "id: 1, name: John Doe ....")
                       PersonDTO owner) {

    public LocalDTO(Local local){
        this(
                local.getId(),
                local.getName(),
                local.getDescription(),
                local.getCep(),
                local.getNumber(),
                local.getCity(),
                local.getStreet(),
                local.getState(),
                local.getComplement(),
                local.getActivity(),
                new PersonDTO(local.getOwner())
        );
    }
}
