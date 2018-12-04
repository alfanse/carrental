package com.asos.carrental.expense.service.client;

import com.asos.carrental.creators.CarRentalExpenseCalculatorFactory;
import com.asos.carrental.expense.service.CarRentalExpenseCalculator;

import java.math.BigDecimal;

public class CarRentalExpenseCalculatorClient {

	CarRentalExpenseCalculator carRentalExpenseCalculator;

	public CarRentalExpenseCalculatorClient() {
		carRentalExpenseCalculator = CarRentalExpenseCalculatorFactory.create();
	}

	public BigDecimal calculateExpense(
			String car, String fuelType, String destination, String tripType, String numberOfPeople, String isAirConditioningRequired) {
		return carRentalExpenseCalculator.calculateExpense(
				car, fuelType, destination, tripType, numberOfPeople, isAirConditioningRequired);
	}



	public static void main(String[] args) {

		CarRentalExpenseCalculatorClient carRentalExpenseCalculatorClient = new CarRentalExpenseCalculatorClient();
		System.out.println(carRentalExpenseCalculatorClient.calculateExpense("car", "deisel", "Mumbai", "return", "6", "true"));
	}
}
