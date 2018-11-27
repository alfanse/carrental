package com.asos.carrental.repository.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.asos.carrental.model.VehicleType;

public class VehicleTypeDiscountRepositoryImplTest {

	VehicleTypeDiscountRepositoryImpl vehicleTypeDiscountRepositoryImplUnderTest;

	@Before
	public void setup() {
		vehicleTypeDiscountRepositoryImplUnderTest = new VehicleTypeDiscountRepositoryImpl();
	}

	@Test
	public void shouldBeTwoPercentForBus() {
		// given
		VehicleType vehicleType = VehicleType.BUS;
		// when
		Float discountByVehicleType = vehicleTypeDiscountRepositoryImplUnderTest
				.findDiscountByVehicleType(vehicleType);
		// then
		assertThat(discountByVehicleType, equalTo(new Float("0.02")));
	}

	@Test
	public void shouldBeNoneForCar() {
		// given
		VehicleType vehicleType = VehicleType.CAR;
		// when
		Float discountByVehicleType = vehicleTypeDiscountRepositoryImplUnderTest
				.findDiscountByVehicleType(vehicleType);
		// then
		assertThat(discountByVehicleType, equalTo(null));
	}
}
