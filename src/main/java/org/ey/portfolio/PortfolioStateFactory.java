package org.ey.portfolio;

import org.ey.enums.PortfolioStatus;

import java.util.Map;

public class PortfolioStateFactory {

    private static final Map<PortfolioStatus, PortfolioState> stateMap = Map.of(
            PortfolioStatus.CLOSED, new ClosedStatus(),
            PortfolioStatus.EMPTY, new EmptyStatus(),
            PortfolioStatus.DEFENSIVE, new DefensiveStatus(),
            PortfolioStatus.ACTIVE, new ActiveStatus(),
            PortfolioStatus.VIP, new VipStatus()
    );

    public static PortfolioState getState(PortfolioStatus status) {
        return stateMap.getOrDefault(status, new DefaultStatus());
    }
}
