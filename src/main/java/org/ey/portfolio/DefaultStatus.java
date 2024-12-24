package org.ey.portfolio;

import org.ey.enums.PortfolioStatus;
import org.ey.enums.ResolutionEvent;

public class DefaultStatus implements PortfolioState {
    @Override
    public PortfolioStatus nextState(ResolutionEvent resultEvent) {
        return switch (resultEvent) {
            case EXTREME_RISK, MARKET_COLLAPSE -> PortfolioStatus.CLOSED; // Casos críticos llevan a CLOSED.
            case BULL -> PortfolioStatus.ACTIVE;                         // Caso BULL lleva a ACTIVE.
            case BEAR -> PortfolioStatus.EMPTY;                          // Caso BEAR lleva a EMPTY.
            default -> PortfolioStatus.DEFENSIVE;                        // Otros eventos llevan a DEFENSIVE.
        };
    }
}

