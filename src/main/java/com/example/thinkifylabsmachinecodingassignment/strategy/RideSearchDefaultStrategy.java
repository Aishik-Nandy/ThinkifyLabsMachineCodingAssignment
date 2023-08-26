package com.example.thinkifylabsmachinecodingassignment.strategy;

import com.example.thinkifylabsmachinecodingassignment.model.Driver;
import com.example.thinkifylabsmachinecodingassignment.repository.DriverRepository;
import com.example.thinkifylabsmachinecodingassignment.repository.UserRepository;
import com.example.thinkifylabsmachinecodingassignment.utils.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;


public class RideSearchDefaultStrategy implements RideSearchStrategy {

    @Override
    public List<Driver> findRides() {
        return ApplicationContextProvider
                .getApplicationContext()
                .getBean(DriverRepository.class)
                .getDatabase()
                .values()
                .stream()
                .toList();
    }
}
