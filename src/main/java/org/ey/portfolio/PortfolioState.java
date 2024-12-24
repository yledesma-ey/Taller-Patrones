package org.ey.portfolio;

import org.ey.enums.PortfolioStatus;
import org.ey.enums.ResolutionEvent;

public interface PortfolioState {
    PortfolioStatus nextState(ResolutionEvent resultEvent);
}
