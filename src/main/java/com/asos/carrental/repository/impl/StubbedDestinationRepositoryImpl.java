package com.asos.carrental.repository.impl;

import java.util.HashMap;
import java.util.Map;

import com.asos.carrental.repository.DestinationRepository;

public class StubbedDestinationRepositoryImpl implements DestinationRepository {

    private Map<String, Float> destinationDistances = new HashMap<>();

    public StubbedDestinationRepositoryImpl() {
        destinationDistances.put("Mumbai", new Float("200"));
        destinationDistances.put("Bangalore", new Float("1000"));
        destinationDistances.put("Delhi", new Float("2050"));
        destinationDistances.put("Chennai", new Float("1234.5"));
        destinationDistances.put("Kolkata", new Float("1800"));
    }

    @Override
    public Float findDestinationDistance(String destination) {
        return destinationDistances.get(destination);
    }

}
