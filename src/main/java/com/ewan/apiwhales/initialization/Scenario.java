package com.ewan.apiwhales.initialization;

import com.ewan.apiwhales.dao.*;
import com.ewan.apiwhales.enumeration.LienDeParenteEnum;
import com.ewan.apiwhales.input.FormInput;
import com.ewan.apiwhales.input.ReservationInput;
import com.ewan.apiwhales.input.SelectionEquipementInput;
import com.ewan.apiwhales.output.EmplacementOutput;
import com.ewan.apiwhales.output.PreparationFormulaireOutput;
import com.ewan.apiwhales.service.ApiWhalesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Scenario implements CommandLineRunner {
    private final ClientDao clientDao;
    private final ConcessionnaireDao concessionnaireDao;
    private final EquipementDao equipementDao;
    private final FileDao fileDao;
    private final LienDeParenteDao lienDeParenteDao;
    private final ParasolDao parasolDao;
    private final PaysDao paysDao;
    private final PlageDao plageDao;
    private final ReservationDao reservationDao;
    private final StatutDao statutDao;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    private final ApiWhalesService apiWhalesService;

    private final boolean IS_CURRENTLY_ACTIVE = false ;
    @Override
    public void run(String...args) throws Exception {
        if(this.IS_CURRENTLY_ACTIVE) {
            runWhenActive(args);
        }
    }
    public void runWhenActive(String...args)  {
       /*
        List<Client> clients = clientDao.findAll();
        System.out.println(clients);
        */

       Long clientId = 5L;
       Long plageId = 1L;
       String aucunLien = LienDeParenteEnum.AUCUN_LIEN.getNom();
       LocalDate dateDebut = LocalDate.of(2020, 6, 4);
       LocalDate dateFin = LocalDate.of(2020, 6, 19);
       FormInput formInput = new FormInput(plageId, dateDebut, dateFin);
       PreparationFormulaireOutput prep = apiWhalesService.preparerFormulaire(formInput);
       List<EmplacementOutput> emplacements = prep.emplacements();
       System.out.println("Nb d'emplacements : " + emplacements.size());
       // System.out.println("P : " +prep.toString());
       Long idx1 = emplacements.get(60).parasolId();
       Long idx2 = emplacements.get(120).parasolId();
       System.out.println("idx1 = " +idx1);
       System.out.println("idx2 = " +idx2);
       List<SelectionEquipementInput> selections= List.of(
               new SelectionEquipementInput(idx1,(byte)1,(byte)0),
                new SelectionEquipementInput(idx2,(byte)0,(byte)1)
        );
       ReservationInput reservationInput=new ReservationInput(clientId,plageId,selections,dateDebut,dateFin,aucunLien);
       // Long newResId =apiPlagesService.effectuerReservation(reservationInput);
       // System.out.println("newResId = " +newResId);
       // apiPlagesService.supprimerReservation(10L);

    }
    public Scenario(
            ClientDao clientDao,
            ConcessionnaireDao concessionnaireDao,
            EquipementDao equipementDao,
            FileDao fileDao,
            LienDeParenteDao lienDeParenteDao,
            ParasolDao parasolDao,
            PaysDao paysDao,
            PlageDao plageDao,
            ReservationDao reservationDao,
            StatutDao statutDao,
            ApiWhalesService apiWhalesService

    ) {
        this.clientDao = clientDao;
        this.concessionnaireDao = concessionnaireDao;
        this.equipementDao = equipementDao;
        this.fileDao = fileDao;
        this.lienDeParenteDao = lienDeParenteDao;
        this.parasolDao = parasolDao;
        this.paysDao = paysDao;
        this.plageDao = plageDao;
        this.reservationDao = reservationDao;
        this.statutDao = statutDao;
        this.apiWhalesService = apiWhalesService;

    }
}
