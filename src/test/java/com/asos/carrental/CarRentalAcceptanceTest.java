package com.asos.carrental;

import com.asos.carrental.expense.service.client.CarRentalExpenseCalculatorClient;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertThat;

public class CarRentalAcceptanceTest {

    @Test
    public void calculateSuppliedExample() {
        CarRentalExpenseCalculatorClient calculatorClient = new CarRentalExpenseCalculatorClient();

        BigDecimal cost = calculatorClient.calculateExpense("car", "deisel", "Mumbai", "return", "1", "true");

        assertThat(cost, CoreMatchers.equalTo(new BigDecimal("5600.00")));
    }


    @Test
    public void calculateSuppliedExampleWithExcessPassengers() {
        CarRentalExpenseCalculatorClient calculatorClient = new CarRentalExpenseCalculatorClient();

        BigDecimal cost = calculatorClient.calculateExpense("car", "deisel", "Mumbai", "return", "5", "true");

        assertThat(cost, CoreMatchers.equalTo(new BigDecimal("6000.00")));
    }
}
