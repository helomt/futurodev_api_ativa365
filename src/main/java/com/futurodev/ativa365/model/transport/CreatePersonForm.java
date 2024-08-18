package com.futurodev.ativa365.model.transport;

import com.futurodev.ativa365.model.enums.GenderEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "Classe para criar um novo usuário")
public record CreatePersonForm(
        @Schema(description = "Nome do usuário", example = "John Doe")
        @NotBlank String name,
                               @Schema(description = "Gênero do usuário", example = "MALE")
                               @NotNull GenderEnum gender,
                               @Schema(description = "CPF do usuário", example = "00000000001")
                               @NotBlank @Valid String cpf,
                               @Schema(description = "Data de nascimento", example = "1988-12-25")
                               @NotNull LocalDate birthday,
                               @Schema(description = "Email do usuário", example = "john.doe@exemplo.com")
                               @NotBlank String email,
                               @Schema(description = "Senha de acesso ao sistema criada pelo usuário", example = "strongpassword")
                               @NotBlank String password,
                               @Schema(description = "Cep de endereço do usuário", example = "88035-000")
                               @NotBlank @Valid String cep,
                                @Schema(description = "Número do endereço do usuário", example = "100")
                               String number,
                                @Schema(description = "Complemento do endereço do usuário", example = "Bloco A")
                               String complement) {
}
