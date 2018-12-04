package com.asos.carrental.expense.service.impl;

import com.asos.carrental.creators.VehicleDirector;
import com.asos.carrental.expense.service.CarRentalExpenseCalculator;
import com.asos.carrental.model.Vehicle;
import com.asos.carrental.utils.calculators.DistanceCalculator;
import com.asos.carrental.utils.calculators.ExpenseCalculator;
import com.asos.carrental.utils.calculators.RateCalculator;

import java.math.BigDecimal;

public class CarRentalExpenseCalculatorImpl
		implements CarRentalExpenseCalculator {

	private final VehicleDirector vehicleDirector;
	private final DistanceCalculator distanceCalculator;
	private final RateCalculator finalRateCalculator;
	private final ExpenseCalculator expenseCalculator;

	public CarRentalExpenseCalculatorImpl(VehicleDirector vehicleDirector,
			DistanceCalculator distanceCalculator,
			RateCalculator finalRateCalculator,
			ExpenseCalculator expenseCalculator) {
		this.vehicleDirector = vehicleDirector;
		this.distanceCalculator = distanceCalculator;
		this.finalRateCalculator = finalRateCalculator;
		this.expenseCalculator = expenseCalculator;
	}

	@Override
	public BigDecimal calculateExpense(String vehicleType,
									   String fuelType,
									   String destination,
									   String tripType,
									   String numberOfPeopleTravelling,
									   String isAirConditioningRequired) {

		Vehicle vehicle = buildVehicle(vehicleType, fuelType, isAirConditioningRequired);

		Float distance = calculateDistance(destination, tripType);

		BigDecimal vehicleRate = calculateVehicleRate(vehicle);

		return calculateTripExpense(numberOfPeopleTravelling, vehicle, distance, vehicleRate);
	}

	private Vehicle buildVehicle(String vehicleType,
                                 String fuelType,
                                 String isAirConditioningRequired) {

		return vehicleDirector.buildVehicle(vehicleType, fuelType, isAirConditioningRequired);

	}

	private Float calculateDistance(String destination, String tripType) {
		return distanceCalculator.getTotalDistance(destination, tripType);
	}

	private BigDecimal calculateVehicleRate(Vehicle vehicle) {
		return finalRateCalculator.calulateRate(vehicle);
	}

	private BigDecimal calculateTripExpense(String numberOfPeopleTravelling,
											Vehicle vehicle,
											Float distance,
											BigDecimal vehicleRate) {

		return expenseCalculator.calculateExpense(vehicle, numberOfPeopleTravelling, distance, vehicleRate);

	}

}
