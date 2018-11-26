package com.asos.carrental.utils.calculators.impl;

import com.asos.carrental.model.TripType;
import com.asos.carrental.repository.DestinationRepository;
import com.asos.carrental.utils.TypeFetcher;
import com.asos.carrental.utils.calculators.DistanceCalculator;

public class DistanceCalculatorImpl implements DistanceCalculator {

    private final DestinationRepository destinationRepository;

    public DistanceCalculatorImpl(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Override
    public Float getTotalDistance(String destination, String tripTypeName) {

        Float distanceToDestination = getDestinationDistance(destination);
        TripType tripType = fetchTripType(tripTypeName);
        return calculateTotalDistance(distanceToDestination, tripType);

    }

    private Float calculateTotalDistance(Float distanceToDestination, TripType tripType) {
        if (tripType == TripType.RETURN) {
            return distanceToDestination * 2;
        } else {
            return distanceToDestination;
        }
    }

    private Float getDestinationDistance(String destination) {
        return destinationRepository.getDestinationDistance(destination);
    }

    private TripType fetchTripType(String tripTypeName) {
        TripType tripType = TypeFetcher.getType(TripType.class, tripTypeName);
        if (tripType == null) {
            tripType = TripType.RETURN;
        }
        return tripType;
    }

}
