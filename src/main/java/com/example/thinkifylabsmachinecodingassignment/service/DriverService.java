package com.example.thinkifylabsmachinecodingassignment.service;

import com.example.thinkifylabsmachinecodingassignment.model.Driver;
import com.example.thinkifylabsmachinecodingassignment.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    @Autowired
    DriverRepository driverRepository;

    public int registerDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public int updateDriver(Driver driver) {
        return driverRepository.update(driver);
    }

}
