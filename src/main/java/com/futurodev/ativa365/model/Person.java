package com.futurodev.ativa365.model;

import com.futurodev.ativa365.model.enums.GenderEnum;
import com.futurodev.ativa365.model.transport.CreatePersonForm;
import com.futurodev.ativa365.model.transport.ViaCepDTO;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Person implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 8)
    private String cep;

    @Column
    private String number;

    @Column
    private String street;

    @Column
    private String complement;

    @Column
    private String city;

    @Column
    private String state;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @OneToMany(mappedBy = "owner")
    private List<Local> localList = new ArrayList<>();

    public Person(){
    }

    public Person(CreatePersonForm form, String password){
        this.name = form.name();
        this.gender = form.gender();
        this.cpf = form.cpf();
        this.birthday = form.birthday();
        this.email = form.email();
        this.password = password;
        this.cep = form.cep();
        this.number = form.number();
        this.complement = form.complement();
    }

    public Person(CreatePersonForm form, PasswordEncoder passwordEncoder){
        this.name = form.name();
        this.gender = form.gender();
        this.cpf = form.cpf();
        this.birthday = form.birthday();
        this.email = form.email();
        this.password = passwordEncoder.encode(form.password());
        this.cep = form.cep();
        this.number = form.number();
        this.complement = form.complement();
    }

    public Person(CreatePersonForm form, PasswordEncoder passwordEncoder, ViaCepDTO address){
        this.name = form.name();
        this.gender = form.gender();
        this.cpf = form.cpf();
        this.birthday = form.birthday();
        this.email = form.email();
        this.password = passwordEncoder.encode(form.password());
        this.cep = form.cep();
        this.number = form.number();
        this.complement = form.complement();
        this.street = address.logradouro();
        this.city = address.localidade();
        this.state = address.uf();
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
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

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}
