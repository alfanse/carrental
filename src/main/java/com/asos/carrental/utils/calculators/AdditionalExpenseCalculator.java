package com.asos.carrental.utils.calculators;

import java.math.BigDecimal;

import com.asos.carrental.model.Vehicle;

public interface AdditionalExpenseCalculator {

	BigDecimal calculateAdditionalExpense(Vehicle vehicle,
			String numberOfPeopleTravelling, Float distance);

}
