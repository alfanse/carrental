package com.asos.carrental.expense.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.asos.carrental.creators.VehicleDirector;
import com.asos.carrental.expense.service.CarRentalExpenseCalculator;
import com.asos.carrental.model.Vehicle;
import com.asos.carrental.utils.calculators.AdditionalExpenseCalculator;
import com.asos.carrental.utils.calculators.DiscountCalculator;
import com.asos.carrental.utils.calculators.DistanceCalculator;
import com.asos.carrental.utils.calculators.RateCalculator;

public class CarRentalExpenseCalculatorImpl
		implements CarRentalExpenseCalculator {

	private VehicleDirector vehicleDirector;
	private DistanceCalculator distanceCalculator;
	private RateCalculator rateCalculator;
	private DiscountCalculator discountCalculator;
	private AdditionalExpenseCalculator additionalExpenseCalculator;

	@Override
	public BigDecimal calculateExpense(String vehicleType, String fuelType,
			String destination, String tripType,
			String numberOfPeopleTravelling, String isAirConditioningRequired) {

		Vehicle vehicle = vehicleDirector.buildVehicle(vehicleType, fuelType,
				isAirConditioningRequired);

		Float distance = distanceCalculator.getTotalDistance(destination,
				tripType);

		BigDecimal vehicleRate = calculateVehicleRate(vehicle);

		BigDecimal totalTripExpense = calculateTripExpnse(
				numberOfPeopleTravelling, vehicle, distance, vehicleRate);

		return totalTripExpense;
	}

	private BigDecimal calculateTripExpnse(String numberOfPeopleTravelling,
			Vehicle vehicle, Float distance, BigDecimal vehicleRate) {
		BigDecimal distanceExpense = vehicleRate
				.multiply(new BigDecimal(distance))
				.setScale(2, RoundingMode.HALF_UP);

		BigDecimal addedExpense = additionalExpenseCalculator
				.calculateAdditionalExpense(vehicle, numberOfPeopleTravelling,
						distance);

		BigDecimal totalTripExpense = distanceExpense.add(addedExpense)
				.setScale(2, RoundingMode.HALF_UP);
		return totalTripExpense;
	}

	private BigDecimal calculateVehicleRate(Vehicle vehicle) {
		BigDecimal standardRate = rateCalculator.calulateRate(vehicle);
		BigDecimal discountedRate = discountCalculator
				.calulateDiscount(standardRate, vehicle);
		return discountedRate;
	}

}
