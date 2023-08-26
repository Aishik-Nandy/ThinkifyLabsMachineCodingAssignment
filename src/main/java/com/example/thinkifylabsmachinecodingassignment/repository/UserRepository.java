package com.example.thinkifylabsmachinecodingassignment.repository;

import com.example.thinkifylabsmachinecodingassignment.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    private int id;
    private Map<Integer, User> database;

    public UserRepository() {
        id = 0;
        database = new HashMap<>();
    }

    public int save(User user) {
        id++;
        user.setId(id);
        database.put(id, user);
        return id;
    }

    public int update(User user) {
        User currentDbEntry = database.get(user.getId());
        currentDbEntry.update(user.getName(), user.getGender(), user.getAge());
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, User> getDatabase() {
        return database;
    }

    public void setDatabase(Map<Integer, User> database) {
        this.database = database;
    }
}
