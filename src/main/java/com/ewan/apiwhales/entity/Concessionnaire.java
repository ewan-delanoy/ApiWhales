package com.ewan.apiwhales.entity;

import com.ewan.apiwhales.input.ConcessionnaireInput;
import com.ewan.apiwhales.output.ConcessionnaireOutput;
import jakarta.persistence.Entity;


@Entity
public class Concessionnaire extends Utilisateur {

    private String numeroDeTelephone;

    // Le no-args constructor exigé par JPA
    protected Concessionnaire() {
        super();
    }
    public Concessionnaire(String nom, String prenom, String email, String motDePasse, String numeroDeTelephone) {
        super(nom,prenom,email,motDePasse);
        this.numeroDeTelephone = numeroDeTelephone;
    }


    public Concessionnaire(ConcessionnaireInput concessionnaireInput) {
        super(concessionnaireInput.nom(),
                concessionnaireInput.prenom(),
                concessionnaireInput.email(),
                concessionnaireInput.motDePasse());
        this.numeroDeTelephone = concessionnaireInput.numeroDeTelephone();
    }

    public ConcessionnaireOutput toOutput() {
        return new ConcessionnaireOutput(this.nom,this.prenom,this.email,this.motDePasse,
               this.numeroDeTelephone);

    }


    public Long getUtilisateurId() {return this.utilisateurId; }
    public String getNom() {return this.nom; }
    public String getPrenom() {return this.prenom;}
    public String getEmail() {return this.email; }
    public String getMotDePasse() {return this.motDePasse; }
    public String getNumeroDeTelephone() {return this.numeroDeTelephone;}

}
