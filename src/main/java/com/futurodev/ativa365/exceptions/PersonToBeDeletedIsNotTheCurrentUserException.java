package com.futurodev.ativa365.exceptions;

public class PersonToBeDeletedIsNotTheCurrentUser extends Exception {
    public PersonToBeDeletedIsNotTheCurrentUser(Long id){
        super(String.format("The user for id %d is not the same logged in", id));
    }
}
