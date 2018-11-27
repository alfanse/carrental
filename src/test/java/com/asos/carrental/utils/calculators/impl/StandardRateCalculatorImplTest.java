package com.asos.carrental.utils.calculators.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.asos.carrental.creators.VehicleBuilder;
import com.asos.carrental.model.FuelType;
import com.asos.carrental.model.Vehicle;
import com.asos.carrental.repository.RateRepository;

@RunWith(MockitoJUnitRunner.class)
public class StandardRateCalculatorImplTest {
	private static final BigDecimal PETROL_FUEL_RATE = new BigDecimal("15.00");
	private static final BigDecimal DEISEL_FUEL_RATE = PETROL_FUEL_RATE
			.subtract(new BigDecimal("1.00"));

	@Mock
	private RateRepository mockedRateRepository;
	StandardRateCalculatorImpl standardRateCalculatorImplUnderTest;

	@Before
	public void setup() {
		standardRateCalculatorImplUnderTest = new StandardRateCalculatorImpl(
				mockedRateRepository);

	}

	@Test
	public void shouldProvideRateForPetrolFuelType() {
		// given
		FuelType fuelType = FuelType.PETROL;
		VehicleBuilder vehicleBuilder = new VehicleBuilder()
				.withFuelType(fuelType);
		Vehicle vehicle = vehicleBuilder.build();
		when(mockedRateRepository.findRateByFuelType(fuelType))
				.thenReturn(PETROL_FUEL_RATE);
		// when
		BigDecimal calulatedRate = standardRateCalculatorImplUnderTest
				.calulateRate(vehicle);
		// then
		assertThat(calulatedRate, equalTo(PETROL_FUEL_RATE));
	}

	@Test
	public void shouldProvideRateForDeiselFuelType() {
		// given
		FuelType fuelType = FuelType.DEISEL;
		VehicleBuilder vehicleBuilder = new VehicleBuilder()
				.withFuelType(fuelType);
		Vehicle vehicle = vehicleBuilder.build();
		when(mockedRateRepository.findRateByFuelType(fuelType))
				.thenReturn(DEISEL_FUEL_RATE);
		// when
		BigDecimal calulatedRate = standardRateCalculatorImplUnderTest
				.calulateRate(vehicle);
		// then
		assertThat(calulatedRate, equalTo(DEISEL_FUEL_RATE));
	}
}
