package com.ewan.apiwhales.entity;


import jakarta.persistence.*;

import java.util.List;


@Entity
public class Plage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plageId;


    private String nom;

    @OneToMany(mappedBy="plage")
    private List<File> files;

    @ManyToOne
    private Concessionnaire concessionnaire;

    // No-args constructor demandé par JPA
    protected Plage() {
        super();
    }
    // Constructeur où l'ID est géré automatiquement par JPA
    public Plage(String nom,Concessionnaire concessionnaire) {
        super() ;
        this.nom = nom;
        this.concessionnaire = concessionnaire;
    }


    public Long getPlageId() {return this.plageId;}
    public String getNom() {return this.nom; }
    public Concessionnaire getConcessionnaire() {return this.concessionnaire; }

}	
