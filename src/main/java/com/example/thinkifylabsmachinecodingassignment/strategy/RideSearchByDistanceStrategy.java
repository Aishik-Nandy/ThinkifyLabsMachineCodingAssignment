package com.example.thinkifylabsmachinecodingassignment.strategy;

import com.example.thinkifylabsmachinecodingassignment.model.Driver;
import com.example.thinkifylabsmachinecodingassignment.model.Location;
import com.example.thinkifylabsmachinecodingassignment.repository.DriverRepository;
import com.example.thinkifylabsmachinecodingassignment.utils.ApplicationContextProvider;

import java.util.Comparator;
import java.util.List;


public class RideSearchByDistanceStrategy implements RideSearchStrategy {

    private Location userSource;
    private Location userDestination;

    public RideSearchByDistanceStrategy(Location userSource, Location userDestination) {
        this.userSource = userSource;
        this.userDestination = userDestination;
    }

    @Override
    public List<Driver> findRides() {
        return ApplicationContextProvider.getApplicationContext()
                .getBean(DriverRepository.class)
                .getDatabase()
                .values()
                .stream()
                .sorted(new Comparator<Driver>() { // sorts driver in ascending order of their distance from the user
                    @Override
                    public int compare(Driver driver1, Driver driver2) {
                        return Double.compare(calculateDistanceFromUser(userSource, driver1.getLocation()),
                                calculateDistanceFromUser(userSource, driver2.getLocation()));
                    }

                    private double calculateDistanceFromUser(Location userSource, Location driverSource) {
                        int userX = userSource.getxCoordinate();
                        int userY = userSource.getyCoordinate();
                        int driverX = driverSource.getxCoordinate();
                        int driverY = driverSource.getyCoordinate();
                        return Math.sqrt((userX - driverX) * (userX - driverX) + (userY - driverY) * (userX - driverY));
                    }
                })
                .toList();
    }

}
