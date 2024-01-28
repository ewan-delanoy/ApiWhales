package com.ewan.apiwhales.enumeration;

public enum LienDeParenteEnum {
    FRERE_SOEUR("Frère/Soeur"),
    COUSIN_E("Cousin/Cousine"),
    AUCUN_LIEN("Aucun lien");

    private String nom;

    LienDeParenteEnum(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
    public float getCoefficient() {
        return switch (this) {
            case FRERE_SOEUR -> 0.5f;
            case COUSIN_E -> 0.75f;
            case AUCUN_LIEN -> 1f;
        };
    }

}
