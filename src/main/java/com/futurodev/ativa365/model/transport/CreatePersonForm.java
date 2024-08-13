package com.futurodev.ativa365.model.transport;

import com.futurodev.ativa365.model.enums.GenderEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreatePersonForm(@NotBlank String name,
                               @NotNull GenderEnum gender,
                               @NotBlank String cpf,
                               @NotNull LocalDate birthday,
                               @NotBlank String email,
                               @NotBlank String password,
                               @NotBlank String cep) {
}
