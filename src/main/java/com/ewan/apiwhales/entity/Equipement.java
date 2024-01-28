package com.ewan.apiwhales.entity;

import com.ewan.apiwhales.enumeration.EquipementEnum;
import com.ewan.apiwhales.output.EquipementOutput;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Equipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipementId;

    private byte nbDeLits;
    private byte nbDeFauteuils;

    protected Equipement() {
        super();
    }

    // Constructeur où l'ID est géré automatiquement par JPA
    // Utilisé dans la phase de remplissage initiale des tables
    public Equipement(byte nbDeLits, byte nbDeFauteuils) {
        this();
        this.nbDeLits = nbDeLits;
        this.nbDeFauteuils = nbDeFauteuils;
    }

    public EquipementOutput toOutput() {
        return new EquipementOutput(this.nbDeLits,this.nbDeFauteuils);
    }



    public Equipement(EquipementEnum equipementEnum) {
        this(equipementEnum.getNbDeLits (),
                equipementEnum.getNbDeFauteuils());
    }


    public String getDescription() {
        String lit = this.nbDeLits==0?"":(this.nbDeLits==1?"1 lit":(this.nbDeLits)+" lits");
        String virgule = ( this.nbDeLits!=0 && this.nbDeFauteuils!=0)?", ":"";
        String fauteuil = this.nbDeFauteuils==0?"":(this.nbDeFauteuils==1?"1 fauteuil":(this.nbDeFauteuils)+" fauteuils");
        return lit + virgule  + fauteuil;
    }

    public Long getEquipementId() {return this.equipementId;}
    public byte getNbDeLits() {return this.nbDeLits; }
    public byte getNbDeFauteuils() {return this.nbDeFauteuils; }
}

