package com.asos.carrental.expense.service.client;

import java.math.BigDecimal;

import com.asos.carrental.creators.CarRentalExpenseCalculatorFactory;
import com.asos.carrental.expense.service.CarRentalExpenseCalculator;

public class CarRentalExpenseCalculatorClient {

	CarRentalExpenseCalculator carRentalExpenseCalculator;

	public CarRentalExpenseCalculatorClient() {
		carRentalExpenseCalculator = CarRentalExpenseCalculatorFactory.create();
	}

	public BigDecimal calculateExpense() {
		return carRentalExpenseCalculator.calculateExpense("car", "deisel",
				"Mumbai", "return", "6", "true");
	}

	public static void main(String[] args) {

		CarRentalExpenseCalculatorClient carRentalExpenseCalculatorClient = new CarRentalExpenseCalculatorClient();
		System.out.println(carRentalExpenseCalculatorClient.calculateExpense());
	}
}
