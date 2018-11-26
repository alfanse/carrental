package com.asos.carrental.repository.impl;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.asos.carrental.model.VehicleSize;
import com.asos.carrental.model.VehicleType;

public class VehicleSizesDelegateTest {

    VehicleSizesDelegate vehicleSizesDelegateUnderTest;

    @Before
    public void setup() {
        vehicleSizesDelegateUnderTest = new VehicleSizesDelegate();
    }

    @Test
    public void shouldBeLargeForBus() {
        //given
        VehicleType vehicleType = VehicleType.BUS;

        //when
        VehicleSize fetchedVehicleSize = vehicleSizesDelegateUnderTest.fetchVehicleSize(vehicleType);

        //then
        assertThat(fetchedVehicleSize, equalTo(VehicleSize.LARGE));
    }

    @Test
    public void shouldBeSmallForCAR() {
        //given
        VehicleType vehicleType = VehicleType.CAR;

        //when
        VehicleSize fetchedVehicleSize = vehicleSizesDelegateUnderTest.fetchVehicleSize(vehicleType);

        //then
        assertThat(fetchedVehicleSize, equalTo(VehicleSize.SMALL));
    }

    @Test
    public void shouldBeLargeForSUV() {
        //given
        VehicleType vehicleType = VehicleType.SUV;

        //when
        VehicleSize fetchedVehicleSize = vehicleSizesDelegateUnderTest.fetchVehicleSize(vehicleType);

        //then
        assertThat(fetchedVehicleSize, equalTo(VehicleSize.LARGE));
    }

    @Test
    public void shouldBeLargeForVAN() {
        //given
        VehicleType vehicleType = VehicleType.VAN;

        //when
        VehicleSize fetchedVehicleSize = vehicleSizesDelegateUnderTest.fetchVehicleSize(vehicleType);

        //then
        assertThat(fetchedVehicleSize, equalTo(VehicleSize.LARGE));
    }

}
