package com.futurodev.ativa365.model;

import com.futurodev.ativa365.model.enums.ActivityEnum;
import com.futurodev.ativa365.model.transport.CreateLocalForm;
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

    @Column(nullable = false)
    private String cep;

    @Column
    private String number;

    @Column
    private String localidade;

    @Column
    private String logradouro;

    @Column
    private String uf;

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
        this.localidade = form.localidade();
        this.logradouro = form.logradouro();
        this.uf = form.uf();
        this.complement = form.complement();
        this.activity = form.activity();
        this.owner = owner;
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

    public String getLocalidade() {
        return localidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getUf() {
        return uf;
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
