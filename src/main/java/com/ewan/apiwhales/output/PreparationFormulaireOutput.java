package com.ewan.apiwhales.output;

import java.util.List;

public record PreparationFormulaireOutput(List<EmplacementOutput> emplacements, List<EquipementOutput> equipements, List<LienDeParenteOutput> liensDeParente, List<PaysOutput> paysEnvisageables) {
}
