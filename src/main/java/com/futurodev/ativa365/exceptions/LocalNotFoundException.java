package com.futurodev.ativa365.exceptions;

public class LocalNotFoundException extends Exception {
    public LocalNotFoundException(Long id) {
        super(String.format("The local for id %d was not found", id));
    }
}
