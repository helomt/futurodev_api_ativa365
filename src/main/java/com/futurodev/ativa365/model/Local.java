package com.futurodev.ativa365.model;

import com.futurodev.ativa365.model.enums.ActivityEnum;
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


}
