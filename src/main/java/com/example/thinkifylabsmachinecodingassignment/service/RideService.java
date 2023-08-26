package com.example.thinkifylabsmachinecodingassignment.service;

import com.example.thinkifylabsmachinecodingassignment.exception.DriverNotAvailableException;
import com.example.thinkifylabsmachinecodingassignment.exception.NoRidesAvailableException;
import com.example.thinkifylabsmachinecodingassignment.factory.RideSearchStrategyFactory;
import com.example.thinkifylabsmachinecodingassignment.model.*;
import com.example.thinkifylabsmachinecodingassignment.repository.DriverRepository;
import com.example.thinkifylabsmachinecodingassignment.strategy.RideSearchStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class RideService {

    @Autowired
    TicketService ticketService;
    @Autowired
    DriverRepository driverRepository;

    private static final ReentrantLock LOCK = new ReentrantLock(true);

    public List<Driver> getAvailableRides(User user, Location source, Location destination, RideSearchMode rideSearchMode)
                                          throws NoRidesAvailableException {

        RideSearchStrategy rideSearchStrategy = RideSearchStrategyFactory.create(rideSearchMode, source, destination);

        List<Driver> rides = rideSearchStrategy.findRides();

        List<Driver> availbleRides = rides.stream().filter(driver -> driver.getStatus() == DriverStatus.AVAILABLE).toList();

        // result
        if (availbleRides.isEmpty()) {
            throw new NoRidesAvailableException("no rides available for userId : " + user.getId()
                                                + " at time : " + System.currentTimeMillis());
        }
        else {
            return availbleRides;
        }
    }

    public Ticket bookRide(User user, Driver driver, Location source, Location destination) throws DriverNotAvailableException {
        if (driver.getStatus() != DriverStatus.AVAILABLE) {
            throw new DriverNotAvailableException("driver with id : " + driver.getId() + " is currently unavailable");
        }
        LOCK.lock();
        try {
            if (driver.getStatus() != DriverStatus.AVAILABLE) {
                throw new DriverNotAvailableException("driver with id : " + driver.getId()
                                                      + " is currently unavailable"
                                                      + "at time : " + System.currentTimeMillis());
            }

            // if user selects a driver who was shown available in his app, that driver is set LOCKED for a certain time
            // as in 'temporarily unavailable' for booking to any other user, this status will change to UNAVAILABLE once
            // user completes payment or to AVAILABLE if payment fails.
            driver.setStatus(DriverStatus.LOCKED);
            driverRepository.update(driver);

            // here it is assumed that payment is always a SUCCESS
            driver.setStatus(DriverStatus.UNAVAILABLE);
            driverRepository.update(driver);
            return ticketService.generateTicket(user, driver, source, destination);
        }
        finally {
            LOCK.unlock();
        }
    }
}
