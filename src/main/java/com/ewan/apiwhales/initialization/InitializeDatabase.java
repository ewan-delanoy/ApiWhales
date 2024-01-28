package com.ewan.apiwhales.initialization;


import com.ewan.apiwhales.entity.*;
import com.ewan.apiwhales.dao.*;
import com.ewan.apiwhales.enumeration.EquipementEnum;
import com.ewan.apiwhales.enumeration.LienDeParenteEnum;
import com.ewan.apiwhales.enumeration.StatutEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class InitializeDatabase implements CommandLineRunner {

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

    private Equipement equipement1 = null;
    private Equipement equipement2 = null;
    private Equipement equipement3 = null;
    private Equipement equipement4 = null;
    private Equipement equipement5 = null;

    private LienDeParente lienDeParente1 = null;

    private LienDeParente lienDeParente2 = null;

    private LienDeParente lienDeParente3 = null;

    private Pays paysFR = null;
    private Pays paysGB = null;
    private Pays paysIT = null;
    private Pays paysPT = null;

    private Concessionnaire concessionnaire1 = null;
    private Concessionnaire concessionnaire2 = null;

    private Client client1 = null;
    private Client client2 = null;
    private Client client3 = null;

    private Statut statutAttente = null;
    private Statut statutAcceptee = null;
    private Statut statutRefusee = null;

    private Plage plage1 = null;
    private Plage plage2 = null;

    private final boolean IS_CURRENTLY_ACTIVE = true ;
    @Override
    public void run(String...args) throws Exception {
       if(this.IS_CURRENTLY_ACTIVE) {
           runWhenActive(args);
       }
    }
    public void runWhenActive(String...args)  {
        ajouterEquipements();
        ajouterLiensDeParente();
        ajouterPays();
        ajouterConcessionnaires();
        ajouterClients();
        ajouterStatuts();
        ajouterPlages();
        ajouterFiles();
        ajouterParasols();
        ajouterReservations();
        System.out.println("Initialization finished");

    }
    private void ajouterClients() {
        if (clientDao.count() == 0) {
            this.client1 = new Client(
                    "DUPONT", "Alexandre", "alexdupond@sfr.fr",
                    encoder.encode("abcdefghi"), this.paysFR
            );
            this.client2 = new Client(
                    "SEYMOUR", "Jane", "jseymour@gmail.uk",
                    encoder.encode("abcdefghi"), this.paysGB
            );
            this.client3 = new Client(
                    "DE ALMEIDA", "Pedro", "pdealmeida@yahoo.pt",
                    encoder.encode("abcdefghi"), this.paysPT
            );
            clientDao.saveAll(Arrays.asList(
                    this.client1,this.client2,this.client3
            ));

        }
    }
    private void ajouterConcessionnaires() {
        if (concessionnaireDao.count() == 0) {
            this.concessionnaire1 = new Concessionnaire(
                    "ROSSI", "Giuseppe", "peppe@humanbooster.fr",
                    encoder.encode("12345678"), "0612345678");
            this.concessionnaire2 = new Concessionnaire(
                    "BULL", "John", "johnthebull@wanadoo.uk",
                    encoder.encode("87654321"), "0687654321");
            concessionnaireDao.saveAll(Arrays.asList(
                    this.concessionnaire1,this.concessionnaire2
            ));
        }
    }
    private void ajouterEquipements() {
        // On teste si des equipements sont déjà en base
        if (equipementDao.count()==0) {
            // il n'y a pas encore d'équipements en base, on en ajoute 5
            this.equipement1 = new Equipement(EquipementEnum.UN_LIT);
            this.equipement2 = new Equipement(EquipementEnum.DEUX_LITS);
            this.equipement3 = new Equipement(EquipementEnum.UN_LIT_UN_FAUTEUIL);
            this.equipement4 = new Equipement(EquipementEnum.UN_FAUTEUIL);
            this.equipement5 = new Equipement(EquipementEnum.DEUX_FAUTEUILS);

            equipementDao.saveAll(Arrays.asList(
                    this.equipement1,this.equipement2,this.equipement3,
                    this.equipement4,this.equipement5
                    ));
        }
    }

    private void ajouterFiles() {
        // On teste si des files sont déjà en base
        if (fileDao.count()==0) {
            for (Plage plage: plageDao.findAll()) {
                // Pour chaque plage, on ajoute 8 files
                for (byte i = 1; i <=8; i++) {
                    fileDao.save(new File(plage, i));
                }
            }

        }
    }
    private void ajouterLiensDeParente() {
        if (lienDeParenteDao.count()==0) {

            this.lienDeParente1 = new LienDeParente(LienDeParenteEnum.FRERE_SOEUR);
            this.lienDeParente2 = new LienDeParente(LienDeParenteEnum.COUSIN_E);
            this.lienDeParente3 = new LienDeParente(LienDeParenteEnum.AUCUN_LIEN);
            lienDeParenteDao.saveAll(Arrays.asList(
                    this.lienDeParente1,this.lienDeParente2,this.lienDeParente3
            ));
        }
    }

    private void ajouterParasols() {
        // On teste si des files sont déjà en base
        if (parasolDao.count()==0) {
            for (File file: fileDao.findAll()) {
                // Pour chaque plage, on ajoute 36 files
                for (byte numEmplacement = 1; numEmplacement <=36; numEmplacement++) {
                    parasolDao.save(new Parasol(file, numEmplacement));
                }
            }

        }
    }
    private void ajouterPays() {
        if (paysDao.count() == 0) {
            this.paysFR = new Pays("FR", "France");
            this.paysGB = new Pays("GB", "Royaume-Uni");
            this.paysIT = new Pays("IT", "Italie");
            this.paysPT = new Pays("PT", "Portugal");
            paysDao.saveAll(Arrays.asList(
                   this.paysFR,this.paysGB,this.paysIT,this.paysPT));
        }
    }

    private void ajouterPlages() {
        if (plageDao.count() == 0) {
            this.plage1 = new Plage("Cala Goloritzé", this.concessionnaire1);
            this.plage2 = new Plage("Acceptée", this.concessionnaire2);

            plageDao.saveAll(Arrays.asList(
                    this.plage1,this.plage2));
        }
    }

    private Parasol para1(int numeroFile,int numEmplacement) {
        return parasolDao.findByFilePlageAndFileNumeroAndNumEmplacement(this.plage1,(byte)numeroFile,(byte)numEmplacement);
    }

    private Parasol para2(int numeroFile,int numEmplacement) {
        return parasolDao.findByFilePlageAndFileNumeroAndNumEmplacement(this.plage2,(byte)numeroFile,(byte)numEmplacement);
    }

    private List<Parasol> lpara1(int numeroFile, int numEmplacement) {
        return Arrays.asList(para1(numeroFile,numEmplacement),para1(numeroFile,numEmplacement+1));
    }

    private List<Parasol> lpara2(int numeroFile,int numEmplacement) {
        return Arrays.asList(para2(numeroFile,numEmplacement),para2(numeroFile,numEmplacement+1));
    }

    private void resa1(Client client,int numFile,int numEmplacement,
                       int year, int month, int dayOfMonth,
                       LienDeParente lienDeParente,Statut statut) {
        Reservation reservation = new Reservation(
                client, lpara1(numFile,numEmplacement),
                  LocalDate.of(year, month, dayOfMonth),
                LocalDate.of(year, month, dayOfMonth+2),
                lienDeParente,statut);
        reservationDao.save(reservation);
    }

    private void ajouterReservations() {
        if (reservationDao.count()==0) {
           resa1(this.client1,4,18,2020,6,3,this.lienDeParente1,this.statutAcceptee);
           resa1(this.client1,4,21,2020,6,10,this.lienDeParente1,this.statutAcceptee);
           resa1(this.client1,4,24,2020,6,17,this.lienDeParente1,this.statutAcceptee);


           resa1(this.client2,4,20,2020,6,4,this.lienDeParente2,this.statutAcceptee);
           resa1(this.client2,4,23,2020,6,11,this.lienDeParente2,this.statutAcceptee);
           resa1(this.client2,4,26,2020,6,18,this.lienDeParente2,this.statutAcceptee);


           resa1(this.client3,4,22,2020,6,5,this.lienDeParente3,this.statutAcceptee);
           resa1(this.client3,4,25,2020,6,12,this.lienDeParente3,this.statutAcceptee);
           resa1(this.client3,4,28,2020,6,19,this.lienDeParente3,this.statutAcceptee);

        }

    }
    private void ajouterStatuts() {
        if (statutDao.count()==0) {
            this.statutAttente = new Statut(StatutEnum.EN_ATTENTE.getNom());
            this.statutAcceptee = new Statut(StatutEnum.ACCEPTEE.getNom());
            this.statutRefusee = new Statut(StatutEnum.REFUSEE.getNom());
            statutDao.saveAll(Arrays.asList(
                    this.statutAttente,this.statutAcceptee,this.statutRefusee));
        }
    }

    public InitializeDatabase(
            ClientDao clientDao,
            ConcessionnaireDao concessionnaireDao,
            EquipementDao equipementDao,
            FileDao fileDao,
            LienDeParenteDao lienDeParenteDao,
            ParasolDao parasolDao,
            PaysDao paysDao,
            PlageDao plageDao,
            ReservationDao reservationDao,
            StatutDao statutDao

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

    }

}
