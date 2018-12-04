package com.asos.carrental.utils.calculators.impl;

import com.asos.carrental.model.Vehicle;
import com.asos.carrental.repository.RateRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class AdditionalTravellerExpenseCalculatorImplTest {
    private static final int MAX_PASSENGER_CAPACITY = 4;
    private static final Vehicle VEHICLE = givenVehicleWithMaxCapacity(MAX_PASSENGER_CAPACITY);
    private static final BigDecimal ADDITIONAL_RATE = new BigDecimal("5").setScale(2);
    private static final Float DISTANCE = Float.valueOf("100");

    private RateRepository rateRepository = mock(RateRepository.class);
    private AdditionalTravellerExpenseCalculatorImpl calculator = new AdditionalTravellerExpenseCalculatorImpl(rateRepository);

    @Test
    public void calculateTripWhenAdditionalPassengers() {
        givenAdditionalTravelerRate();

        int additionalPassengers = 2;
        BigDecimal bigDecimal = calculator.calculateAdditionalExpense(
                VEHICLE,
                MAX_PASSENGER_CAPACITY + additionalPassengers + "",
                DISTANCE);

        BigDecimal expectedAdditionalExpense = ADDITIONAL_RATE.multiply(new BigDecimal(additionalPassengers * DISTANCE));
        assertThat(bigDecimal, CoreMatchers.equalTo(expectedAdditionalExpense));
    }

    @Test
    public void calculateTripWhenLessThanMaxPassengerCapacity() {
        givenAdditionalTravelerRate();

        Vehicle vehicle = givenVehicleWithMaxCapacity(MAX_PASSENGER_CAPACITY);

        BigDecimal bigDecimal = calculator.calculateAdditionalExpense(vehicle, MAX_PASSENGER_CAPACITY + "", DISTANCE);
        assertThat(bigDecimal, CoreMatchers.equalTo(BigDecimal.ZERO));
    }

    private static Vehicle givenVehicleWithMaxCapacity(int maxPassengerCapacity) {
        Vehicle vehicle = new Vehicle();
        vehicle.setMaxPassengerCapacity(maxPassengerCapacity);
        return vehicle;
    }

    private void givenAdditionalTravelerRate() {
        Mockito.when(rateRepository.findAdditionalTravellerRate()).thenReturn(ADDITIONAL_RATE);
    }
}