package org.ey.portfolio;

import org.ey.enums.PortfolioStatus;
import org.ey.enums.ResolutionEvent;

public class DefensiveStatus implements PortfolioState {

    @Override
    public PortfolioStatus nextState(ResolutionEvent resultEvent) {
        return switch (resultEvent) {
            case EXTREME_RISK, OUT_OF_INVESTORS -> PortfolioStatus.CLOSED;
            case BEAR, DEBT_DEFAULT, MARKET_COLLAPSE -> PortfolioStatus.EMPTY;
            case BULL -> PortfolioStatus.ACTIVE;
            default -> PortfolioStatus.DEFENSIVE; // Retorna el mismo estado por defecto
        };
    }
}
