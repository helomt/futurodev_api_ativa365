package com.futurodev.ativa365.model.transport;

import com.futurodev.ativa365.model.enums.ActivityEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateLocalForm(@NotBlank String name,
                              @NotBlank String description,
                              @NotBlank  @Valid String cep,
                              String number,
                              String complement,
                              @NotNull ActivityEnum activity) {
}
