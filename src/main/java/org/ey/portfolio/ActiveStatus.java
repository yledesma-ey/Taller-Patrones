package org.ey.portfolio;

import org.ey.enums.PortfolioStatus;
import org.ey.enums.ResolutionEvent;

public class ActiveStatus implements PortfolioState {
    @Override
    public PortfolioStatus nextState(ResolutionEvent resultEvent) {
        return switch (resultEvent) {
            case ResolutionEvent.OUT_OF_INVESTORS, ResolutionEvent.DEBT_DEFAULT -> PortfolioStatus.DEFENSIVE;
            case ResolutionEvent.MARKET_COLLAPSE, BEAR -> PortfolioStatus.EMPTY;
            case ResolutionEvent.EXTREME_RISK -> PortfolioStatus.CLOSED;
            case ResolutionEvent.BULL -> PortfolioStatus.VIP;
            default -> PortfolioStatus.ACTIVE;
        };
    }
}
