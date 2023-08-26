package com.example.thinkifylabsmachinecodingassignment.service;

import com.example.thinkifylabsmachinecodingassignment.model.Driver;
import com.example.thinkifylabsmachinecodingassignment.model.Location;
import com.example.thinkifylabsmachinecodingassignment.model.Ticket;
import com.example.thinkifylabsmachinecodingassignment.model.User;
import com.example.thinkifylabsmachinecodingassignment.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    protected Ticket generateTicket(User user, Driver driver, Location source, Location destination) {
        return ticketRepository.save(new Ticket(user, driver, source, destination));
    }


}
