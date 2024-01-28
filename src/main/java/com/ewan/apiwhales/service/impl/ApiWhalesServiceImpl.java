package com.ewan.apiwhales.service.impl;

import com.ewan.apiwhales.dao.*;
import com.ewan.apiwhales.entity.*;
import com.ewan.apiwhales.enumeration.StatutEnum;
import com.ewan.apiwhales.input.*;
import com.ewan.apiwhales.output.*;
import com.ewan.apiwhales.service.ApiWhalesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ApiWhalesServiceImpl implements ApiWhalesService {

    private final ClientDao clientDao;

    private final ConcessionnaireDao concessionnaireDao;

    private final EquipementDao equipementDao;

    private final LienDeParenteDao lienDeParenteDao;
    private final PaysDao paysDao;

    private final ParasolDao parasolDao;

    private final PlageDao plageDao;
    private final ReservationDao reservationDao;

    private final StatutDao statutDao;

    public void inscrireNouveauClient(ClientInput clientInput) {
        Client client = new Client(clientInput);
        clientDao.save(client);
    }

    public List<ReservationOutput> reservationsClient (Long utilisateurId,String statutNom) {
        List<Reservation> reservations =
                reservationDao.reservationsPourClient(utilisateurId, statutNom);
        List<ReservationOutput>  reservationsOutput = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationsOutput.add(reservation.toOutput());
        }
        return reservationsOutput;
    }

    public PreparationFormulaireOutput preparerFormulaire(FormInput fInput) {
        Long plageId = fInput.plageId();
        LocalDate dateDebut = fInput.dateDebut();
        LocalDate dateFin = fInput.dateFin();
        Plage plage = plageDao.getReferenceById(plageId);
        List<Long> idsOccupes = parasolDao.idsDesParasolsOccupes(plage,dateDebut,dateFin);
        List<Parasol> parasols = parasolDao.findByFilePlagePlageId(plageId);
        List<EmplacementOutput> emplacementsDisponibles = new ArrayList<>();

        for (Parasol parasol : parasols) {
            if(!(idsOccupes.contains(parasol.getParasolId()))) {
                emplacementsDisponibles.add(parasol.toEmplacementOutput());
            }
        }

        return new PreparationFormulaireOutput(
                emplacementsDisponibles,
                tousLesEquipements(),
                tousLesLiensDeParente(),
                tousLesPays()
        );
    }

    public Long effectuerReservation(ReservationInput reservationInput)
    {
       // Extraire les parametres de l'input
        Long clientId = reservationInput.clientId();
        Long plageId = reservationInput.plageId();
        List<SelectionEquipementInput> selections=reservationInput.selections();
        LocalDate dateDebut = reservationInput.dateDebut();
        LocalDate dateFin = reservationInput.dateFin();
        String lienDeParenteNom = reservationInput.lienDeParenteNom();

        Statut enAttente = statutDao.findByNom(StatutEnum.EN_ATTENTE.getNom());
        Client client = clientDao.findByUtilisateurId(clientId);
        LienDeParente lienDeParente = lienDeParenteDao.findByNom(lienDeParenteNom);
        List<Parasol> parasols = new ArrayList<>();
        for (SelectionEquipementInput selection : selections) {
            Parasol parasol = parasolDao.findByParasolId(selection.parasolId());
            Equipement equipement = equipementDao.findByNbDeLitsAndNbDeFauteuils
                    (selection.nbDeLits(),selection.nbDeFauteuils());
            parasol.setEquipement(equipement);
            parasolDao.save(parasol);
            parasols.add(parasol);
        }
        Reservation reservation = new Reservation(client,parasols,
                dateDebut,dateFin,
               lienDeParente,enAttente);
        reservationDao.save(reservation);
        return reservation.getReservationId();
    }


    public void inscrireNouveauConcessionnaire(ConcessionnaireInput concessionnaireInput) {
        Concessionnaire concessionnaire = new Concessionnaire(concessionnaireInput);
        concessionnaireDao.save(concessionnaire);
    }

    public List<ReservationOutput> reservationsConcessionnaire (Long utilisateurId,String statutNom) {
        List<Reservation> reservations =
                reservationDao.reservationsPourConcessionnaire(utilisateurId, statutNom);
        List<ReservationOutput>  reservationsOutput = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationsOutput.add(reservation.toOutput());
        }
        return reservationsOutput;
    }

    public void accepterReservation (Long reservationId) {
        Reservation reservation = reservationDao.findByReservationId(reservationId);
        reservation.setStatut(statutDao.findByNom(StatutEnum.ACCEPTEE.getNom()));
        reservationDao.save(reservation);
    }

    public void refuserReservation (Long reservationId) {
        Reservation reservation = reservationDao.findByReservationId(reservationId);
        reservation.setStatut(statutDao.findByNom(StatutEnum.REFUSEE.getNom()));
        reservationDao.save(reservation);
    }

    public void supprimerReservation (Long reservationId) {
        reservationDao.deleteByReservationId(reservationId);
    }

    private List<EquipementOutput> tousLesEquipements() {
        List<Equipement> equipements = equipementDao.findAll();
        List<EquipementOutput> equipementsOutput = new ArrayList<>();

        for (Equipement equipement : equipements) {
            equipementsOutput.add(equipement.toOutput());
        }
        return equipementsOutput;
    }

    private List<PaysOutput> tousLesPays() {
        List<Pays> lesPays = paysDao.findAll();
        List<PaysOutput> lesPaysOutput = new ArrayList<>();

        for (Pays pays : lesPays) {
            lesPaysOutput.add(pays.toOutput());
        }
        return lesPaysOutput;
    }

    private List<LienDeParenteOutput> tousLesLiensDeParente() {
        List<LienDeParente> liensDeParente = lienDeParenteDao.findAll();
        List<LienDeParenteOutput> liensDeParenteOutput = new ArrayList<>();

        for (LienDeParente lienDeParente : liensDeParente) {
            liensDeParenteOutput.add(lienDeParente.toOutput());
        }
        return liensDeParenteOutput;
    }

    public ApiWhalesServiceImpl(ClientDao clientDao,
                                ConcessionnaireDao concessionnaireDao,
                                EquipementDao equipementDao,
                                LienDeParenteDao lienDeParenteDao,
                                PaysDao paysDao,
                                ParasolDao parasolDao,
                                PlageDao plageDao,
                                ReservationDao reservationDao,
                                StatutDao statutDao
                                ) {
        this.clientDao = clientDao ;
        this.concessionnaireDao = concessionnaireDao;
        this.equipementDao = equipementDao;
        this.lienDeParenteDao = lienDeParenteDao;
        this.paysDao = paysDao ;
        this.parasolDao = parasolDao ;
        this.plageDao = plageDao;
        this.reservationDao = reservationDao;
        this.statutDao = statutDao;
    }
}
