package com.ewan.apiwhales.output;

import java.util.List;

public record ReservationOutput(List<ParasolOutput> parasols,
                                ClientOutput client, ConcessionnaireOutput concessionnaire,
                                LienDeParenteOutput lienDeParente, String statutNom
                                ) {
}
