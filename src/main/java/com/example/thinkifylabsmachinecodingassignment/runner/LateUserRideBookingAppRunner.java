package com.example.thinkifylabsmachinecodingassignment.runner;

import com.example.thinkifylabsmachinecodingassignment.exception.DriverNotAvailableException;
import com.example.thinkifylabsmachinecodingassignment.exception.NoRidesAvailableException;
import com.example.thinkifylabsmachinecodingassignment.model.*;
import com.example.thinkifylabsmachinecodingassignment.service.RideService;
import com.example.thinkifylabsmachinecodingassignment.utils.ApplicationContextProvider;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.logging.Logger;

// this runner class has a thread delay ie the thread using this runnable will mostly act last
public class LateUserRideBookingAppRunner implements Runnable {
    private User user;
    private Location source;
    private Location destination;

    private static final Logger LOGGER = Logger.getLogger(LateUserRideBookingAppRunner.class.getName());

    public LateUserRideBookingAppRunner(User user, Location source, Location destination) {
        this.user = user;
        this.source = source;
        this.destination = destination;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        RideService rideService = ApplicationContextProvider.getApplicationContext().getBean(RideService.class);
        Ticket ticket = null;
        List<Driver> rides = null;
        // user gets a list of rides, say he/she selects the search mode to prioritise drivers closer to them
        try {
            rides = rideService.getAvailableRides(user, source, destination, RideSearchMode.SEARCH_BASED_ON_DISTANCE);
        } catch (NoRidesAvailableException e) {
            LOGGER.info("Sorry! " + user.getName() + " no rides available :( ");
            return;
        }

        // user selects a ride, say he/she selects the closest ride
        try {
            ticket = rideService.bookRide(user, rides.get(0), source, destination);
        } catch (DriverNotAvailableException e) {
            LOGGER.info("Sorry! " + user.getName() + " your selected driver is currently unavailable :/ Please select someone else :)");
        }

        // print ticket details
        System.out.println(ticket.toString());

    }
}
