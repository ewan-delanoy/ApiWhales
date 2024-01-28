package com.ewan.apiwhales.output;


import java.time.LocalDateTime;

public record ClientOutput(String nom, String prenom, String email, String motDePasse, PaysOutput pays,
                           LocalDateTime dateHeureInscription) {
}
