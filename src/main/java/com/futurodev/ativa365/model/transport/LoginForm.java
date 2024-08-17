package com.futurodev.ativa365.model.transport;

import jakarta.validation.constraints.NotBlank;

public record LoginForm(@NotBlank String username,
                        @NotBlank String password) {
}
