package org.ey.portfolio;

import org.ey.enums.PortfolioStatus;
import org.ey.enums.ResolutionEvent;

public class ClosedStatus implements PortfolioState {

    @Override
    public PortfolioStatus nextState(ResolutionEvent resultEvent) {
        return switch (resultEvent) {
            default -> PortfolioStatus.CLOSED;
        };
    }
}
