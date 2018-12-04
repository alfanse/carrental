package com.asos.carrental.expense.service;

import java.math.BigDecimal;

public interface CarRentalExpenseCalculator {

	/**
	 * 
	 * @param vehicleType               The type of selected vehicle
	 *                                  (Car/Bus/Van etc.)
	 * @param fuelType                  The type of selected fuel (Deisel /
	 *                                  Petrol). This Defaults to Deisel for
	 *                                  large vehicles.
	 * @param destination               The selected destination
	 * @param tripType                  The type of trip (Single/Return)
	 * @param numberOfPeople            The number of people traveling
	 * @param isAirConditioningRequired The selected option of air condition
	 * @return
	 */
	BigDecimal calculateExpense(String vehicleType,
								String fuelType,
								String destination,
								String tripType,
								String numberOfPeople,
								String isAirConditioningRequired);}
