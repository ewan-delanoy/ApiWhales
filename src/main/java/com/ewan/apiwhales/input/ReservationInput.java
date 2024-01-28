package com.ewan.apiwhales.input;



import java.time.LocalDate;
import java.util.List;

public record ReservationInput (Long clientId, Long plageId,
                                List<SelectionEquipementInput> selections, LocalDate dateDebut, LocalDate dateFin,
                                String lienDeParenteNom) {

}
