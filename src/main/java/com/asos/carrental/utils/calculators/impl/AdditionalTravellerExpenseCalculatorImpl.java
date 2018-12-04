package com.asos.carrental.utils.calculators.impl;

import com.asos.carrental.model.Vehicle;
import com.asos.carrental.repository.RateRepository;
import com.asos.carrental.utils.calculators.AdditionalExpenseCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AdditionalTravellerExpenseCalculatorImpl
		implements AdditionalExpenseCalculator {

	private final RateRepository rateRepository;

	public AdditionalTravellerExpenseCalculatorImpl(
			RateRepository rateRepository) {
		this.rateRepository = rateRepository;
	}

	@Override
	public BigDecimal calculateAdditionalExpense(Vehicle vehicle, String numberOfPeopleTravelling, Float distance) {

		int travellerCount = Integer.parseInt(numberOfPeopleTravelling);
		int maxPassengerCapacity = vehicle.getMaxPassengerCapacity();

		if (travellerCount > maxPassengerCapacity) {
			int additionalTravellerCount = travellerCount - maxPassengerCapacity;
			BigDecimal additionalTravellerRate = rateRepository.findAdditionalTravellerRate();
			return additionalTravellerRate.multiply(
					new BigDecimal(distance)).multiply(
							new BigDecimal(additionalTravellerCount)).setScale(2, RoundingMode.HALF_UP);
		}
		return BigDecimal.ZERO;
	}

}
