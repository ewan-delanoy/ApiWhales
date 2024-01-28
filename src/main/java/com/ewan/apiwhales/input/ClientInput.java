package com.ewan.apiwhales.input;

import java.time.LocalDateTime;

public record ClientInput(String nom, String prenom, String email, String motDePasse,
                          PaysInput paysInput, LocalDateTime dateHeureInscription) {
}
