package com.ewan.apiwhales.entity;

import java.util.List;


import com.ewan.apiwhales.input.PaysInput;
import com.ewan.apiwhales.output.PaysOutput;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity // Annotation de JPA pour demander la création d'une table pays
public class Pays {

    @Id
    @Column(length=2)
    private String code;

    // @NonNull
    // @Size(min=2, message="Le nom du caractère doit comporter au moins {min} caractères")
    private String nom;


    @OneToMany(mappedBy="pays", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    // @ToString.Exclude
    @JsonIgnore
    private List<Client> clients;

    // No-args constructor demandé par JPA
    protected Pays() {
        super();
    }
    public Pays(String code,String nom) {
        this.code = code ;
        this.nom = nom;
    }

    public Pays(PaysInput paysInput) {

        this.code = paysInput.code();
        this.nom =  paysInput.nom();

    }

    public PaysOutput toOutput() {
        return new PaysOutput(this.code,this.nom);
    }

    public String getCode() { return this.code; }
    public String getNom() { return this.nom; }
}