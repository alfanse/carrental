package com.asos.carrental.repository.impl;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.asos.carrental.model.FuelType;
import com.asos.carrental.model.VehicleSize;
import com.asos.carrental.model.VehicleType;

@RunWith(MockitoJUnitRunner.class)
public class StubbedVehicleRepositoryImplTest {

    @Mock
    private AirConditionDelegate airConditionDelegate;
    @Mock
    private FuelTypeDelegate fuelTypeDelegate;
    @Mock
    private VehiclePassengerCapacitiesDelegate vehiclePassengerCapacitiesDelegate;
    @Mock
    private VehicleSizesDelegate vehicleSizesDelegate;

    StubbedVehicleRepositoryImpl stubbedVehicleRepositoryImplUnderTest;

    @Before
    public void setup() {
        stubbedVehicleRepositoryImplUnderTest = new StubbedVehicleRepositoryImpl(airConditionDelegate, fuelTypeDelegate,
                vehiclePassengerCapacitiesDelegate, vehicleSizesDelegate);

    }

    @Test
    public void shouldBeAirConditioned() {
        //given 
        //when 
        //then
    }

    @Test
    public void shouldBeAirConditionedWhenEvalutedToTrue() {
        //given 
        VehicleType vehicleType = VehicleType.BUS;
        String isAirConditioningRequired = "true";
        when(airConditionDelegate.fectchAirCoditioningAvailibity(vehicleType, isAirConditioningRequired))
                .thenReturn(new Boolean(true));

        //when 
        Boolean fectchedAirCoditioningAvailibity = stubbedVehicleRepositoryImplUnderTest
                .fectchAirCoditioningAvailibity(vehicleType, isAirConditioningRequired);

        //then
        assertThat(fectchedAirCoditioningAvailibity, equalTo(true));
    }

    @Test
    public void shouldNotBeAirConditionedWhenEvalutedToFalse() {
        //given 
        VehicleType vehicleType = VehicleType.CAR;
        String isAirConditioningRequired = "false";
        when(airConditionDelegate.fectchAirCoditioningAvailibity(vehicleType, isAirConditioningRequired))
                .thenReturn(new Boolean(false));

        //when 
        Boolean fectchedAirCoditioningAvailibity = stubbedVehicleRepositoryImplUnderTest
                .fectchAirCoditioningAvailibity(vehicleType, isAirConditioningRequired);

        //then
        assertThat(fectchedAirCoditioningAvailibity, equalTo(false));
    }

    @Test
    public void shouldBePetrolWhenEvalutedToPetrol() {
        //given 
        VehicleType vehicleType = VehicleType.CAR;
        VehicleSize vehicleSize = VehicleSize.SMALL;
        when(vehicleSizesDelegate.fetchVehicleSize(vehicleType)).thenReturn(vehicleSize);
        String fuelType = "petrol";
        when(fuelTypeDelegate.fetchFuelType(vehicleSize, fuelType)).thenReturn(FuelType.PETROL);

        //when 
        FuelType fetchedFuelType = stubbedVehicleRepositoryImplUnderTest.fetchFuelType(vehicleType, fuelType);

        //then
        assertThat(fetchedFuelType, equalTo(FuelType.PETROL));
    }

    @Test
    public void shouldBeDeiselWhenEvalutedToDeisel() {
        //given 
        VehicleType vehicleType = VehicleType.CAR;
        VehicleSize vehicleSize = VehicleSize.SMALL;
        when(vehicleSizesDelegate.fetchVehicleSize(vehicleType)).thenReturn(vehicleSize);
        String fuelType = "deisel";
        when(fuelTypeDelegate.fetchFuelType(vehicleSize, fuelType)).thenReturn(FuelType.DEISEL);

        //when 
        FuelType fetchedFuelType = stubbedVehicleRepositoryImplUnderTest.fetchFuelType(vehicleType, fuelType);

        //then
        assertThat(fetchedFuelType, equalTo(FuelType.DEISEL));
    }

}
