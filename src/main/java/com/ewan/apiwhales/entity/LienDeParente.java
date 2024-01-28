package com.ewan.apiwhales.entity;

import com.ewan.apiwhales.enumeration.LienDeParenteEnum;
import com.ewan.apiwhales.output.LienDeParenteOutput;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class LienDeParente {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long lienDeParenteId;

    private String nom;

    private float coefficient;

    public LienDeParente() {
        super();
    }
    public LienDeParente(String nom, float coefficient) {
        this();
        this.nom = nom;
        this.coefficient = coefficient;
    }

    public LienDeParente(LienDeParenteEnum lienDeParenteEnum) {
        this(lienDeParenteEnum.getNom(),lienDeParenteEnum.getCoefficient());
    }

    public LienDeParenteOutput toOutput() {
        return new LienDeParenteOutput(this.nom,this.coefficient);
    }

    public Long getLienDeParenteId() { return this.lienDeParenteId; }
    public String getNom() { return this.nom; }
    public float getCoefficient() { return this.coefficient; }
}