package com.ewan.apiwhales.service;



import com.ewan.apiwhales.input.*;
import com.ewan.apiwhales.output.PreparationFormulaireOutput;
import com.ewan.apiwhales.output.ReservationOutput;

import java.util.List;

public interface ApiWhalesService {

    // services utilisés par le client
    public void inscrireNouveauClient(ClientInput clientInput);
    public List<ReservationOutput> reservationsClient (Long utilisateurId,String statutNom);
    public PreparationFormulaireOutput preparerFormulaire(FormInput fInput);

    public Long effectuerReservation(ReservationInput reservationInput);

    // services utilisés par le concessionnaire

    public void inscrireNouveauConcessionnaire(ConcessionnaireInput concessionnaireInput);

    public List<ReservationOutput> reservationsConcessionnaire (Long utilisateurId,String statutNom);

    public void accepterReservation (Long reservationId);

    public void refuserReservation (Long reservationId);

    public void supprimerReservation (Long reservationId);



}
