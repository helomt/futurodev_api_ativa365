package com.futurodev.ativa365.exceptions;

import jakarta.validation.constraints.NotBlank;

public class PersonCpfAlreadyExistsException extends Exception {
    public PersonCpfAlreadyExistsException(@NotBlank String cpf) {
        super("User with provided CPF already exists: "+cpf);
    }
}
