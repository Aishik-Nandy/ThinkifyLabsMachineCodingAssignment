package com.example.thinkifylabsmachinecodingassignment.factory;

import com.example.thinkifylabsmachinecodingassignment.model.Location;
import com.example.thinkifylabsmachinecodingassignment.model.RideSearchMode;
import com.example.thinkifylabsmachinecodingassignment.strategy.RideSearchByDistanceStrategy;
import com.example.thinkifylabsmachinecodingassignment.strategy.RideSearchDefaultStrategy;
import com.example.thinkifylabsmachinecodingassignment.strategy.RideSearchStrategy;


public class RideSearchStrategyFactory {
    public static RideSearchStrategy create(RideSearchMode mode, Location source, Location destination) {
        if (mode == RideSearchMode.SEARCH_BASED_ON_DISTANCE) {
            return new RideSearchByDistanceStrategy(source, destination);
        }
        else {
            return new RideSearchDefaultStrategy();
        }
    }
}
