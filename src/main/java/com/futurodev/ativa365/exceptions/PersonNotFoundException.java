package com.futurodev.ativa365.exceptions;

public class PersonNotFoundException extends Exception {
    public PersonNotFoundException(Long id) {
        super(String.format("The user for id %d was not found", id));
    }
}
