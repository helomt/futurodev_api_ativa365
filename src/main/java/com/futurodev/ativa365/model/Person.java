package com.futurodev.ativa365.model;

import com.futurodev.ativa365.model.enums.GenderEnum;
import com.futurodev.ativa365.model.transport.CreatePersonForm;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private LocalDate brithday;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String cep;

    @Column
    private String number;

    @Column
    private String street;

    @Column
    private String complement;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @OneToMany(mappedBy = "owner")
    private List<Local> localList = new ArrayList<>();

    public Person(){
    }

    public Person(CreatePersonForm form){
        this.name = form.name();
        this.gender = form.gender();
        this.cpf = form.cpf();
        this.brithday = form.birthday();
        this.email = form.email();
        this.password = form.password();
        this.cep = form.cep();
        this.number = form.number();
        this.complement = form.complement();
    }

    public void markAsDeleted(){
        this.deleted = true;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getBrithday() {
        return brithday;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCep() {
        return cep;
    }

    public String getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public String getComplement() {
        return complement;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<Local> getLocalList() {
        return localList;
    }
}
