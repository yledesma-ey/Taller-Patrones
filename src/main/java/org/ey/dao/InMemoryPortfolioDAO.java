package org.ey.dao;

import org.ey.enums.PortfolioStatus;
import org.junit.Assert;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class InMemoryPortfolioDAO implements PortfolioDAO{
    private final ConcurrentMap<Long, PortfolioStatus> database;

    public InMemoryPortfolioDAO(){
        database = new ConcurrentHashMap<>();
    }

    @Override
    public PortfolioStatus getPortfolioStatus(Long Id) {
        if(database.get(Id) == null){
            var random = new Random();
            var selected = random.nextInt(PortfolioStatus.values().length - 1);
            var selectedStatus = PortfolioStatus.values()[selected];
            database.put(Id, selectedStatus);
        }
        Assert.assertNotNull(database.get(Id));
        return database.get(Id);
    }

    public PortfolioStatus getPortfolioStatusRaw(Long Id) {
        return database.get(Id);
    }

    @Override
    public void savePortfolioStatus(Long Id, PortfolioStatus status) {
        database.put(Id, status);
    }

}
