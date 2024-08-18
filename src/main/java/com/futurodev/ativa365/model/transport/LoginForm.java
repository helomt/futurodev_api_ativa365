package com.futurodev.ativa365.model.transport;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Classe parar capturar dados de um login de usuário")
public record LoginForm(
        @Schema(description = "Nome de usuário para logar, no caso o email",  example = "john.doe@exemplo.com")
        @NotBlank String username,
        @Schema(description = "Senha de acesso ao sistema cadastrada pelo usuário", example = "strongpassword")
                        @NotBlank String password) {
}
