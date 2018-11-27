package com.asos.carrental.utils.calculators;

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

@RunWith(MockitoJUnitRunner.class)
public class StandardExpenseCalculatorImplTest {

	private static final BigDecimal STANDARD_RATE = new BigDecimal("15.00");

	@Mock
	private AdditionalExpenseCalculator additionalExpenseCalculator;

	StandardExpenseCalculatorImpl standardExpenseCalculatorImplUnderTest;

	private Vehicle vehicle;

	private String numberOfPeopleTravelling;

	private Float distance;

	private BigDecimal vehicleRate;

	@Before
	public void setup() {
		standardExpenseCalculatorImplUnderTest = new StandardExpenseCalculatorImpl(
				additionalExpenseCalculator);

		vehicle = new VehicleBuilder().withMaxPassengerCapacity(4)
				.withVehicleType(VehicleType.CAR).build();
		numberOfPeopleTravelling = "4";
		distance = new Float("2000");
		vehicleRate = STANDARD_RATE;

	}

	@Test
	public void shouldBeStandardExpenseWhenNoAdditionalExpenseIncurred() {

		// given
		when(additionalExpenseCalculator.calculateAdditionalExpense(vehicle,
				numberOfPeopleTravelling, distance))
						.thenReturn(BigDecimal.ZERO);

		// when
		BigDecimal calculatedExpense = standardExpenseCalculatorImplUnderTest
				.calculateExpense(vehicle, numberOfPeopleTravelling, distance,
						vehicleRate);

		// then
		BigDecimal assertedExpense = new BigDecimal("30000.00");
		assertThat(calculatedExpense, equalTo(assertedExpense));

	}

	@Test
	public void shouldBeAddedExpenseWhenAdditionalExpenseIsIncurred() {

		// given
		when(additionalExpenseCalculator.calculateAdditionalExpense(vehicle,
				numberOfPeopleTravelling, distance))
						.thenReturn(new BigDecimal("2000"));

		// when
		BigDecimal calculatedExpense = standardExpenseCalculatorImplUnderTest
				.calculateExpense(vehicle, numberOfPeopleTravelling, distance,
						vehicleRate);

		// then
		BigDecimal assertedExpense = new BigDecimal("32000.00");
		assertThat(calculatedExpense, equalTo(assertedExpense));

	}
}
