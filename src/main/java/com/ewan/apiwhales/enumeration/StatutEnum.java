package com.ewan.apiwhales.enumeration;

public enum StatutEnum {
    EN_ATTENTE("En attente de traitement"),
    ACCEPTEE("Acceptee"),
    REFUSEE("Refusée");

    private String nom;
    StatutEnum(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }

}
