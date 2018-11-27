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
import com.asos.carrental.model.Vehicle;
import com.asos.carrental.model.VehicleType;
import com.asos.carrental.repository.DiscountRepository;

@RunWith(MockitoJUnitRunner.class)
public class VehicleTypeDiscountCalculatorImplTest {

	private static final BigDecimal DEISEL_STANDARD_RATE = new BigDecimal(
			"14.00");

	@Mock
	private DiscountRepository mockedDiscountRepository;

	VehicleTypeDiscountCalculatorImpl vehicleTypeDiscountCalculatorImplUnderTest;

	@Before
	public void setup() {
		vehicleTypeDiscountCalculatorImplUnderTest = new VehicleTypeDiscountCalculatorImpl(
				mockedDiscountRepository);
	}

	@Test
	public void shouldCalculateTheDiscountedRateWhenVehicleTypeIsDiscounted() {
		// given
		BigDecimal standardRate = DEISEL_STANDARD_RATE;
		VehicleType bus = VehicleType.BUS;
		Vehicle vehicle = new VehicleBuilder().withVehicleType(bus).build();
		when(mockedDiscountRepository.findDiscountByVehicleType(bus))
				.thenReturn(new Float("0.02"));
		// when
		BigDecimal discountedRate = vehicleTypeDiscountCalculatorImplUnderTest
				.calulateDiscount(standardRate, vehicle);
		// then
		BigDecimal assertedDiscountedRate = new BigDecimal("13.72");
		assertThat(discountedRate, equalTo(assertedDiscountedRate));

	}

	@Test
	public void shouldReturnTheStandardRateWhenVehicleTypeIsNotDiscounted() {
		// given
		BigDecimal standardRate = DEISEL_STANDARD_RATE;
		VehicleType bus = VehicleType.CAR;
		Vehicle vehicle = new VehicleBuilder().withVehicleType(bus).build();
		when(mockedDiscountRepository.findDiscountByVehicleType(bus))
				.thenReturn(null);
		// when
		BigDecimal discountedAmount = vehicleTypeDiscountCalculatorImplUnderTest
				.calulateDiscount(standardRate, vehicle);
		// then
		BigDecimal assertedDiscountedAmount = DEISEL_STANDARD_RATE;
		assertThat(discountedAmount, equalTo(assertedDiscountedAmount));

	}

	@Test
	public void shouldReturnTheStandardRateWhenDiscountIsZero() {
		// given
		BigDecimal standardRate = DEISEL_STANDARD_RATE;
		VehicleType bus = VehicleType.CAR;
		Vehicle vehicle = new VehicleBuilder().withVehicleType(bus).build();
		when(mockedDiscountRepository.findDiscountByVehicleType(bus))
				.thenReturn(new Float("0.00"));
		// when
		BigDecimal discountedAmount = vehicleTypeDiscountCalculatorImplUnderTest
				.calulateDiscount(standardRate, vehicle);
		// then
		BigDecimal assertedDiscountedAmount = DEISEL_STANDARD_RATE;
		assertThat(discountedAmount, equalTo(assertedDiscountedAmount));

	}

}
