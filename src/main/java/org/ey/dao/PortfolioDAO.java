package org.ey.dao;

import org.ey.enums.PortfolioStatus;


public interface PortfolioDAO {
    PortfolioStatus getPortfolioStatus(final Long Id);

    void savePortfolioStatus(Long Id, PortfolioStatus status);
}
