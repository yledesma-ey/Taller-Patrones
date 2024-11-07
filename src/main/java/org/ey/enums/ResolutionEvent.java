package org.ey.enums;

import java.util.List;

public enum ResolutionEvent {
    EXTREME_RISK(""),
    BULL(""),
    BEAR(""),
    DEBT_DEFAULT(""),
    MARKET_COLLAPSE(""),
    AUDIT_RISK(""),
    OUT_OF_INVESTORS("");

    private final String description;

    ResolutionEvent(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    //Esta méthod retorna la lista resultate inicial para el procesador de reglas.
    public static List<ResolutionEvent> getAllEventsForProcess(){
        return List.of(EXTREME_RISK, BULL, BEAR, DEBT_DEFAULT, MARKET_COLLAPSE, AUDIT_RISK, OUT_OF_INVESTORS);
    }

}
