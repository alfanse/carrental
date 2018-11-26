package com.asos.carrental.expense.service.impl;

import java.math.BigDecimal;

import com.asos.carrental.creators.VehicleDirector;
import com.asos.carrental.expense.service.CarRentalExpenseCalculator;
import com.asos.carrental.model.Vehicle;
import com.asos.carrental.utils.calculators.DistanceCalculator;
import com.asos.carrental.utils.calculators.RateCalculator;

public class CarRentalExpenseCalculatorImpl implements CarRentalExpenseCalculator {

    private VehicleDirector vehicleBuilder;
    private DistanceCalculator distanceCalculator;
    private RateCalculator rateCalculator;

    @Override
    public BigDecimal calculateExpense(String vehicleType, String fuelType, String destination, String tripType,
            String numberOfPeople, String isAirConditioningRequired) {

        //Build vehicle
        Vehicle vehicle = vehicleBuilder.buildVehicle(vehicleType, fuelType, isAirConditioningRequired);

        //Fetch total distance
        Float distance = distanceCalculator.getTotalDistance(destination, tripType);

        //Get Calculated Rate
        BigDecimal calculatedRate = rateCalculator.getCalulateRate(vehicle);

        //get standard rate for vehicle

        //apply discount based on Vehicle Type
        //apply numberOfPeopleRule

        //Total expense is
        // Calculated rate * total Distance

        return null;
    }

}
