package com.asos.carrental.repository.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.asos.carrental.model.VehicleType;

public class AirConditionDelegateTest {

    AirConditionDelegate airConditionDelegateUnderTest;

    @Before
    public void setup() {

        airConditionDelegateUnderTest = new AirConditionDelegate();
    }

    @Test
    public void shouldBeAirConditionedForAllSUVs() {
        //given
        VehicleType vehicleType = VehicleType.SUV;
        String isAirConditioningRequired = null;
        //when
        Boolean airCoditioningAvailibity = airConditionDelegateUnderTest.fectchAirCoditioningAvailibity(vehicleType,
                isAirConditioningRequired);
        //then
        assertThat(airCoditioningAvailibity.booleanValue(), equalTo(true));

    }

    @Test
    public void shouldNotBeAirConditionedWhenNotSelectedAndCarIsNotSUV() {
        //given
        VehicleType vehicleType = VehicleType.BUS;
        String isAirConditioningRequired = "false";
        //when
        Boolean airCoditioningAvailibity = airConditionDelegateUnderTest.fectchAirCoditioningAvailibity(vehicleType,
                isAirConditioningRequired);
        //then
        assertThat(airCoditioningAvailibity.booleanValue(), equalTo(false));

    }

    @Test
    public void shouldBeAirConditionedWhenSelectedAndCarIsNotSUV() {
        //given
        VehicleType vehicleType = VehicleType.CAR;
        String isAirConditioningRequired = "true";
        //when
        Boolean airCoditioningAvailibity = airConditionDelegateUnderTest.fectchAirCoditioningAvailibity(vehicleType,
                isAirConditioningRequired);
        //then
        assertThat(airCoditioningAvailibity.booleanValue(), equalTo(true));

    }

}
