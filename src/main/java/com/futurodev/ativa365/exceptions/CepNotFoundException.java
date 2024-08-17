package com.futurodev.ativa365.exceptions;

public class CepNotFoundException extends Exception {
    public CepNotFoundException(String cep){
        super("The cep informed was not found "+cep);
    }
}
