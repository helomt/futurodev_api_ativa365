package com.futurodev.ativa365.model;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class Person {

    private Long id;
    private String name;
    private String gender;
    private String cpf;
    private LocalDateTime brithday;
    private String email;
    private String password;
    private String cep;
    private String number;
    private String street;
    private String complement;






}
