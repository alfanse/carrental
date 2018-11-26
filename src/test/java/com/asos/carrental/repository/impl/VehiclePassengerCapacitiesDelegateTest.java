package com.asos.carrental.repository.impl;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.asos.carrental.model.VehicleType;

public class VehiclePassengerCapacitiesDelegateTest {

    VehiclePassengerCapacitiesDelegate vehiclePassengerCapacitiesDelegateUnderTest;

    @Before
    public void setup() {
        vehiclePassengerCapacitiesDelegateUnderTest = new VehiclePassengerCapacitiesDelegate();
    }

    @Test
    public void shouldBeTwentyForBus() {
        //given
        VehicleType vehicleType = VehicleType.BUS;

        //when
        Integer fetchedVehiclePassengerCapacity = vehiclePassengerCapacitiesDelegateUnderTest
                .fetchVehiclePassengerCapacity(vehicleType);

        //then
        assertThat(fetchedVehiclePassengerCapacity, equalTo(20));
    }

    @Test
    public void shouldBeFourForCar() {
        //given
        VehicleType vehicleType = VehicleType.CAR;

        //when
        Integer fetchedVehiclePassengerCapacity = vehiclePassengerCapacitiesDelegateUnderTest
                .fetchVehiclePassengerCapacity(vehicleType);

        //then
        assertThat(fetchedVehiclePassengerCapacity, equalTo(4));
    }

    @Test
    public void shouldBeEightForSUV() {
        //given
        VehicleType vehicleType = VehicleType.SUV;

        //when
        Integer fetchedVehiclePassengerCapacity = vehiclePassengerCapacitiesDelegateUnderTest
                .fetchVehiclePassengerCapacity(vehicleType);

        //then
        assertThat(fetchedVehiclePassengerCapacity, equalTo(8));
    }

    @Test
    public void shouldBeSixForVan() {
        //given
        VehicleType vehicleType = VehicleType.VAN;

        //when
        Integer fetchedVehiclePassengerCapacity = vehiclePassengerCapacitiesDelegateUnderTest
                .fetchVehiclePassengerCapacity(vehicleType);

        //then
        assertThat(fetchedVehiclePassengerCapacity, equalTo(6));
    }

}
