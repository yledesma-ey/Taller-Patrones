package org.ey.enums;

public enum PortfolioStatus {
    CLOSED(""),
    EMPTY(""),
    DEFENSIVE(""),
    ACTIVE(""),
    VIP("");

    private final String description;

    PortfolioStatus(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
