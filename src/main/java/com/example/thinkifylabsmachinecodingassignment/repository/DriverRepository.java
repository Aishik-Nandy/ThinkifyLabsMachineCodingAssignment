package com.example.thinkifylabsmachinecodingassignment.repository;

import com.example.thinkifylabsmachinecodingassignment.model.Driver;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class DriverRepository {
    private int id;
    private static Map<Integer, Driver> database;

    public DriverRepository() {
        id = 0;
        database = new ConcurrentHashMap<>();
    }

    public int save(Driver driver) {
        id++;
        driver.setId(id);
        database.put(id, driver);
        return id;
    }

    public int update(Driver driver) {
        Driver currentDbEntry = database.get(driver.getId());
        currentDbEntry.update(driver);
        return driver.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Driver> getDatabase() {
        return database;
    }

    public void setDatabase(Map<Integer, Driver> database) {
        this.database = database;
    }
}
