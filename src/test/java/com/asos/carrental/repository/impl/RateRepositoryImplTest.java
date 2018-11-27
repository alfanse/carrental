package com.asos.carrental.repository.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.asos.carrental.model.FuelType;

public class RateRepositoryImplTest {

	private RateRepositoryImpl rateRepositoryImplUnderTest;

	@Before
	public void setup() {
		rateRepositoryImplUnderTest = new RateRepositoryImpl();
	}

	@Test
	public void shouldProvideRateForPetrol() {
		// given
		FuelType fuelType = FuelType.PETROL;
		// when
		BigDecimal rateByFuelType = rateRepositoryImplUnderTest
				.findRateByFuelType(fuelType);
		// then
		assertThat(rateByFuelType, equalTo(new BigDecimal("15.00")));

	}

	@Test
	public void shouldProvideRateForDeisel() {
		// given
		FuelType fuelType = FuelType.DEISEL;
		// when
		BigDecimal rateByFuelType = rateRepositoryImplUnderTest
				.findRateByFuelType(fuelType);
		// then
		assertThat(rateByFuelType, equalTo(new BigDecimal("14.00")));

	}

}
