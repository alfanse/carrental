package com.asos.carrental.utils.calculators;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.asos.carrental.model.Vehicle;

public class StandardExpenseCalculatorImpl implements ExpenseCalculator {

	private final AdditionalExpenseCalculator additionalExpenseCalculator;

	public StandardExpenseCalculatorImpl(
			AdditionalExpenseCalculator additionalExpenseCalculator) {
		this.additionalExpenseCalculator = additionalExpenseCalculator;
	}

	@Override
	public BigDecimal calculateExpense(Vehicle vehicle,
			String numberOfPeopleTravelling, Float distance,
			BigDecimal vehicleRate) {

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

}
