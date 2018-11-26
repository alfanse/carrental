package com.asos.carrental.repository.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.asos.carrental.model.FuelType;
import com.asos.carrental.model.VehicleSize;

public class FuelTypeDelegateTest {

    FuelTypeDelegate fuelTypeDelegateUnderTest;

    @Before
    public void setup() {
        fuelTypeDelegateUnderTest = new FuelTypeDelegate();
    }

    @Test
    public void shouldBeDiselForLargeVehicles() {
        //given
        VehicleSize vehicleSize = VehicleSize.LARGE;
        String selectedFuelType = null;
        //when
        FuelType fetchedFuelType = fuelTypeDelegateUnderTest.fetchFuelType(vehicleSize, selectedFuelType);
        //then
        assertThat(fetchedFuelType, equalTo(FuelType.DEISEL));
    }

    @Test
    public void shouldBeDiselWhenSelectedForSmallVehicles() {
        //given
        VehicleSize vehicleSize = VehicleSize.SMALL;
        String selectedFuelType = "deisel";
        //when
        FuelType fetchedFuelType = fuelTypeDelegateUnderTest.fetchFuelType(vehicleSize, selectedFuelType);
        //then
        assertThat(fetchedFuelType, equalTo(FuelType.DEISEL));
    }

    @Test
    public void shouldBePetrolWhenSelectedForSmallVehicles() {
        //given
        VehicleSize vehicleSize = VehicleSize.SMALL;
        String selectedFuelType = "petrol";
        //when
        FuelType fetchedFuelType = fuelTypeDelegateUnderTest.fetchFuelType(vehicleSize, selectedFuelType);
        //then
        assertThat(fetchedFuelType, equalTo(FuelType.PETROL));
    }

}
