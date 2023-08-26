package com.example.thinkifylabsmachinecodingassignment;

import com.example.thinkifylabsmachinecodingassignment.model.Driver;
import com.example.thinkifylabsmachinecodingassignment.model.Gender;
import com.example.thinkifylabsmachinecodingassignment.model.Location;
import com.example.thinkifylabsmachinecodingassignment.model.User;
import com.example.thinkifylabsmachinecodingassignment.runner.LateUserRideBookingAppRunner;
import com.example.thinkifylabsmachinecodingassignment.runner.RideBookingAppRunner;
import com.example.thinkifylabsmachinecodingassignment.service.DriverService;
import com.example.thinkifylabsmachinecodingassignment.service.RideService;
import com.example.thinkifylabsmachinecodingassignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class ThinkifyLabsMachineCodingAssignmentApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private RideService rideService;

    public static void main(String[] args) {
        SpringApplication.run(ThinkifyLabsMachineCodingAssignmentApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        System.out.println("hello");
        // randomising all the location inputs for both the users and the drivers
        Random random = new Random();

        // create users
        User user1 = new User("Ash", Gender.MALE, 25);
        User user2 = new User("Lisa", Gender.FEMALE, 23);
        User user3 = new User("Chintu", Gender.MALE, 30);
        userService.registerUser(user1);
        userService.registerUser(user2);
        userService.registerUser(user3);

        // create drivers
        Driver driver1 = new Driver("Driver1", Gender.MALE, 35,
                "Swift, KA-01-12345",
                new Location(random.nextInt(1, 1000), random.nextInt(1, 1000)));
        Driver driver2 = new Driver("Driver2", Gender.MALE, 40,
                "Maruti, KA-24-21313",
                new Location(random.nextInt(1, 1000), random.nextInt(1, 1000)));
        driverService.registerDriver(driver1);
        driverService.registerDriver(driver2);

        // simulating booking of rides by the above users
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Runnable user1BooksRide = new RideBookingAppRunner(user1,
                new Location(random.nextInt(1, 1000), random.nextInt(1, 1000)),
                new Location(random.nextInt(1, 1000), random.nextInt(1, 1000)));
        // this user acts late than the other two users
        Runnable user2BooksRide = new LateUserRideBookingAppRunner(user2,
                new Location(random.nextInt(1, 1000), random.nextInt(1, 1000)),
                new Location(random.nextInt(1, 1000), random.nextInt(1, 1000)));
        Runnable user3BooksRide = new RideBookingAppRunner(user3,
                new Location(random.nextInt(1, 1000), random.nextInt(1, 1000)),
                new Location(random.nextInt(1, 1000), random.nextInt(1, 1000)));

        executorService.execute(user1BooksRide);
        executorService.execute(user2BooksRide);
        executorService.execute(user3BooksRide);
    }

}
