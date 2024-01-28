package com.ewan.apiwhales.input;

import java.time.LocalDate;

public record FormInput(Long plageId, LocalDate dateDebut, LocalDate dateFin) {
}
