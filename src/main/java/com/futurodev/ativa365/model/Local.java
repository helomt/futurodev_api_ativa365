package com.futurodev.ativa365.model;

import com.futurodev.ativa365.model.enums.ActivityEnum;
import com.futurodev.ativa365.model.transport.CreateLocalForm;
import com.futurodev.ativa365.model.transport.UpdateLocalForm;
import com.futurodev.ativa365.model.transport.ViaCepDTO;
import jakarta.persistence.*;

@Entity
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 8)
    private String cep;

    @Column
    private String number;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private String state;

    @Column
    private String complement;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    @Enumerated(EnumType.STRING)
    private ActivityEnum activity;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private Person owner;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    public Local(){

    }

    public Local(CreateLocalForm form, Person owner){
        this.name = form.name();
        this.description = form.description();
        this.cep = form.cep();
        this.number = form.number();
        this.complement = form.complement();
        this.activity = form.activity();
        this.owner = owner;
    }

    public Local(CreateLocalForm form, Person owner, ViaCepDTO address){
        this.name = form.name();
        this.description = form.description();
        this.cep = form.cep();
        this.number = form.number();
        this.city = address.localidade();
        this.street = address.logradouro();
        this.state = address.uf();
        this.complement = form.complement();
        this.activity = form.activity();
        this.owner = owner;
    }

    public void updateAvailableAttributes(UpdateLocalForm form){
        this.name = form.name() != null ? form.name() : this.name;
        this.description = form.description() != null ? form.description() : this.description;
        this.cep= form.cep() != null ? form.cep() : this.cep;
        this.number = form.number() != null ? form.number() : this.number;
        this.complement = form.complement() != null ? form.complement() : this.complement;
        this.activity = form.activity() != null ? form.activity() : this.activity;
    }

    public void updateAvailableAttributes(UpdateLocalForm form, ViaCepDTO address){
        this.name = form.name() != null ? form.name() : this.name;
        this.description = form.description() != null ? form.description() : this.description;
        this.cep= form.cep() != null ? form.cep() : this.cep;
        this.number = form.number() != null ? form.number() : this.number;
        this.city = address.localidade() != null ? address.localidade() : this.city;
        this.street = address.logradouro() != null ? address.logradouro() : this.street;
        this.state = address.uf() != null ? address.uf() : this.state;
        this.complement = form.complement() != null ? form.complement() : this.complement;
        this.activity = form.activity() != null ? form.activity() : this.activity;
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

    public String getDescription() {
        return description;
    }

    public String getCep() {
        return cep;
    }

    public String getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getState() {
        return state;
    }

    public String getComplement() {
        return complement;
    }

    public ActivityEnum getActivity() {
        return activity;
    }

    public Person getOwner() {
        return owner;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
