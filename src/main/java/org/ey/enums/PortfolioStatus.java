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

    public PortfolioStatus nextState(ResolutionEvent resultEvent) {
        switch (resultEvent) {
            case EXTREME_RISK:
                if (this == CLOSED || this == EMPTY || this == DEFENSIVE || this == ACTIVE || this == VIP) {
                    return CLOSED;
                }
            case BULL:
                if (this == EMPTY || this == DEFENSIVE) {
                    return ACTIVE;
                } else if (this == CLOSED) {
                    return CLOSED;
                } else {
                    return VIP;
                }
            case BEAR:
                return (this == CLOSED) ? CLOSED : EMPTY;
            case DEBT_DEFAULT:
                if (this == DEFENSIVE) {
                    return EMPTY;
                } else if (this == CLOSED) {
                    return CLOSED;
                } else if (this != EMPTY) {
                    return DEFENSIVE;
                }
                return DEFENSIVE;
            case OUT_OF_INVESTORS:
                if (this == ACTIVE) {
                    return DEFENSIVE;
                } else if (this == DEFENSIVE) {
                    return CLOSED;
                }
                break;
            case MARKET_COLLAPSE:
                return (this == VIP || this == CLOSED) ? CLOSED : EMPTY;
            default:
                return this; // No cambia el estado para otros eventos
        }
        return this;
    }
}
