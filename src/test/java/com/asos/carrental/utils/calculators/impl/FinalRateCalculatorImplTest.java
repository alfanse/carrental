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

import com.asos.carrental.model.Vehicle;
import com.asos.carrental.utils.calculators.DiscountCalculator;
import com.asos.carrental.utils.calculators.RateCalculator;

@RunWith(MockitoJUnitRunner.class)
public class FinalRateCalculatorImplTest {

	private static final BigDecimal STANDARD_RATE = new BigDecimal("15.00");
	private static final BigDecimal DISCOUNTED_RATE = new BigDecimal("12.00");
	@Mock
	private RateCalculator standardRateCalculator;
	@Mock
	private DiscountCalculator discountCalculator;

	FinalRateCalculatorImpl finalRateCalculatorImpl;

	@Before
	public void setup() {
		finalRateCalculatorImpl = new FinalRateCalculatorImpl(
				standardRateCalculator, discountCalculator);
	}

	@Test
	public void shouldReturnStandardRateWhenDiscountIsNotProvided() {
		// given
		Vehicle vehicle = new Vehicle();
		when(standardRateCalculator.calulateRate(vehicle))
				.thenReturn(STANDARD_RATE);
		when(discountCalculator.calulateDiscount(STANDARD_RATE, vehicle))
				.thenReturn(STANDARD_RATE);
		// when
		BigDecimal finalRate = finalRateCalculatorImpl.calulateRate(vehicle);
		// then
		assertThat(finalRate, equalTo(STANDARD_RATE));
	}

	@Test
	public void shouldReturnDiscountedRateWhenDiscountIsProvided() {
		// given
		Vehicle vehicle = new Vehicle();
		when(standardRateCalculator.calulateRate(vehicle))
				.thenReturn(STANDARD_RATE);
		when(discountCalculator.calulateDiscount(STANDARD_RATE, vehicle))
				.thenReturn(DISCOUNTED_RATE);
		// when
		BigDecimal finalRate = finalRateCalculatorImpl.calulateRate(vehicle);
		// then
		assertThat(finalRate, equalTo(DISCOUNTED_RATE));
	}

}
