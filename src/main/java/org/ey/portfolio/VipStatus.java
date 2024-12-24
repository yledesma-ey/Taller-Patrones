package org.ey.portfolio;

import org.ey.enums.PortfolioStatus;
import org.ey.enums.ResolutionEvent;

public class VipStatus implements PortfolioState {
    @Override
    public PortfolioStatus nextState(ResolutionEvent resultEvent) {
        return switch (resultEvent) {
            case EXTREME_RISK, MARKET_COLLAPSE -> PortfolioStatus.CLOSED;       // Cambia a CLOSED en caso de EXTREME_RISK.
            case BEAR -> PortfolioStatus.EMPTY;               // Cambia a EMPTY en caso de BEAR.
            case DEBT_DEFAULT -> PortfolioStatus.DEFENSIVE; // Cambia a DEFENSIVE en caso de OUT_OF_INVESTORS.
            default -> PortfolioStatus.VIP;                   // Permanece en VIP para otros eventos.
        };
    }
}