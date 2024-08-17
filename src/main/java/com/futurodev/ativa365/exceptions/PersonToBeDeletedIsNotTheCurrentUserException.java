package com.futurodev.ativa365.exceptions;

public class PersonToBeDeletedIsNotTheCurrentUserException extends Exception {
    public PersonToBeDeletedIsNotTheCurrentUserException(Long id){
        super(String.format("The user for id %d is not the same logged in", id));
    }
}
