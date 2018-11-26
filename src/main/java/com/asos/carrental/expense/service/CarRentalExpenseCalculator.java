package com.asos.carrental.expense.service;

import java.math.BigDecimal;

public interface CarRentalExpenseCalculator {


	BigDecimal calculateExpense(
			String vehicleType,
			String fuelType, 
			String destination,
			String tripType,
			String numberOfPeople,
			String isAirConditioningRequired);
}
