package com.futurodev.ativa365.exceptions;

import jakarta.validation.constraints.NotBlank;

public class PersonEmailAlreadyExistsException extends Exception {
    public PersonEmailAlreadyExistsException(@NotBlank String email){
        super("User with provided email already exists: "+email);
    }
}
