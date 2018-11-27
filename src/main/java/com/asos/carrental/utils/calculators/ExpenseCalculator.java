package com.asos.carrental.utils.calculators;

import java.math.BigDecimal;

import com.asos.carrental.model.Vehicle;

public interface ExpenseCalculator {

	BigDecimal calculateExpense(Vehicle vehicle,
			String numberOfPeopleTravelling, Float distance,
			BigDecimal vehicleRate);

}
