package com.example.thinkifylabsmachinecodingassignment.repository;

import com.example.thinkifylabsmachinecodingassignment.model.Ticket;
import com.example.thinkifylabsmachinecodingassignment.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TicketRepository {
    private static int id;
    private static Map<Integer, Ticket> database;

    public TicketRepository() {
        this.id = 0;
        this.database = new HashMap<>();
    }

    public Ticket save(Ticket ticket) {
        id++;
        ticket.setId(id);
        database.put(id, ticket);
        return ticket;
    }
}
