package com.ewan.apiwhales.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import com.ewan.apiwhales.output.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;



import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToMany
    private List<Parasol> parasols;

    @ManyToOne
    // @NotNull(message="Merci de préciser le client")
    private Client client;

    @DateTimeFormat(iso=ISO.DATE)
    // @NotNull(message="Merci de préciser la date de début de la réservation")
    private LocalDate dateDebut;

    @DateTimeFormat(iso=ISO.DATE)
    private LocalDate dateFin;

    private double montantAReglerEnEuros;
    private String numeroCarte ;

    private byte moisExpiration ;

    private short anneeExpiration ;

    private String cryptogramme ;

    @Lob  // Large Object
    private String remarques;

    // Une réservation a un statut
    // Cette annotation va créer une clé étrangère
    // dans la table reservation
    @ManyToOne
    private Statut statut;

    @ManyToOne
    private LienDeParente lienDeParente;

    // No-args constructor demandé par JPA
    protected Reservation() {
        super();
    }
    public Reservation(Client client,List<Parasol> parasols,
                       LocalDate dateDebut,LocalDate dateFin,
                       LienDeParente lienDeParente,Statut statut) {
        this.client = client ;
        this.parasols = parasols;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lienDeParente = lienDeParente;
        this.statut = statut;
        this.montantAReglerEnEuros = 100;
    }

    public ReservationOutput toOutput() {
        File file = this.parasols.get(0).getFile();
        List<ParasolOutput> parasolsOutput = new ArrayList<>();
        for (Parasol parasol : this.parasols) {
            parasolsOutput.add(parasol.toOutput());
        }
        return new  ReservationOutput(parasolsOutput ,
                this.client.toOutput(), file.getPlage().getConcessionnaire().toOutput(),
                this.lienDeParente.toOutput(), this.statut.getNom()
        );

    }


    public Long getReservationId() {return this.reservationId;}
    public Client getClient() {return this.client; }
    public List<Parasol> getParasols() {return this.parasols; }
    public LocalDate getDateDebut() {return this.dateDebut; }
    public LocalDate getDateFin() {return this.dateFin; }

    public LienDeParente getLienDeParente() {return this.lienDeParente; }

    public Statut getStatut() {return this.statut; }

    public void setStatut(Statut newStatut) {this.statut = newStatut; }

}