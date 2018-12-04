package com.asos.carrental.expense.service.impl;

import com.asos.carrental.creators.VehicleBuilder;
import com.asos.carrental.creators.VehicleDirector;
import com.asos.carrental.model.FuelType;
import com.asos.carrental.model.Vehicle;
import com.asos.carrental.model.VehicleSize;
import com.asos.carrental.model.VehicleType;
import com.asos.carrental.utils.calculators.DistanceCalculator;
import com.asos.carrental.utils.calculators.ExpenseCalculator;
import com.asos.carrental.utils.calculators.RateCalculator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarRentalExpenseCalculatorImplTest {

	private static final String AIR_COND_REQUIRED_TRUE = "true";
	private static final String TRAVELLER_COUNT_FOUR = "4";
	private static final String TRIP_TYPE_RETURN = "return";
	private static final String DESTINATION_MUMBAI = "Mumbai";
	private static final String FUEL_TYPE_PETROL = "petrol";
	private static final String VEHICLE_TYPE_CAR = "car";

	@Mock
	private VehicleDirector vehicleDirector;
	@Mock
	private DistanceCalculator distanceCalculator;
	@Mock
	private RateCalculator finalRateCalculator;
	@Mock
	private ExpenseCalculator expenseCalculator;

	@InjectMocks
	CarRentalExpenseCalculatorImpl carRentalExpenseCalculatorImplUnderTest;

	private Vehicle swift;
	private Float distanceToMumbaiAndBack;
	private BigDecimal rateForSwift;
	private BigDecimal tripExpenseToMumbaiInASwift;

	@Before
	public void setup() {
		swift = new VehicleBuilder().withFuelType(FuelType.PETROL)
				.withVehicleType(VehicleType.CAR)
				.withIsAirConditioned(true)
				.withMaxPassengerCapacity(4)
				.withVehicleSize(VehicleSize.SMALL)
				.build();

		distanceToMumbaiAndBack = new Float("400");
		rateForSwift = new BigDecimal("15.00");
		tripExpenseToMumbaiInASwift = new BigDecimal("6000.00");
	}

	@Test
	public void shouldCalculateTotalExpense() {
		// given
		givenTripInSwiftToMumbai();

		// when
		BigDecimal calculatedExpense = whenCalculateExpenseInSwiftToMubai();

		// then
		assertThat(calculatedExpense, equalTo(tripExpenseToMumbaiInASwift));
	}

	@Test
	public void shouldVerifyVehicleBuilderParameters() {

		// given
		givenTripInSwiftToMumbai();

		// when
		whenCalculateExpenseInSwiftToMubai();

		// then
		verify(vehicleDirector).buildVehicle(VEHICLE_TYPE_CAR, FUEL_TYPE_PETROL, AIR_COND_REQUIRED_TRUE);
	}

	@Test
	public void shouldVerifyDistanceCalculatorParameters() {
		// given
		givenTripInSwiftToMumbai();

		// when
		whenCalculateExpenseInSwiftToMubai();

		// then

		verify(distanceCalculator).getTotalDistance(DESTINATION_MUMBAI, TRIP_TYPE_RETURN);
	}

	@Test
	public void shouldVerifyRateCalculatorParameters() {
		// given
		givenTripInSwiftToMumbai();

		// when
		whenCalculateExpenseInSwiftToMubai();

		// then
		verify(finalRateCalculator).calulateRate(swift);
	}

	@Test
	public void shouldVerifyExpenseCalculatorParameters() {
		// given
		givenTripInSwiftToMumbai();

		// when
		whenCalculateExpenseInSwiftToMubai();

		// then
		verify(expenseCalculator).calculateExpense(swift, TRAVELLER_COUNT_FOUR, distanceToMumbaiAndBack, rateForSwift);
	}

	private void givenTripInSwiftToMumbai() {
		when(vehicleDirector.buildVehicle(VEHICLE_TYPE_CAR, FUEL_TYPE_PETROL,
				AIR_COND_REQUIRED_TRUE)).thenReturn(swift);
		when(distanceCalculator.getTotalDistance(DESTINATION_MUMBAI, TRIP_TYPE_RETURN))
				.thenReturn(distanceToMumbaiAndBack);
		when(finalRateCalculator.calulateRate(swift)).thenReturn(rateForSwift);

		when(expenseCalculator.calculateExpense(swift, TRAVELLER_COUNT_FOUR, distanceToMumbaiAndBack, rateForSwift)).thenReturn(tripExpenseToMumbaiInASwift);
	}

	private BigDecimal whenCalculateExpenseInSwiftToMubai() {
		return carRentalExpenseCalculatorImplUnderTest.calculateExpense(
				VEHICLE_TYPE_CAR,
				FUEL_TYPE_PETROL,
				DESTINATION_MUMBAI,
				TRIP_TYPE_RETURN,
				TRAVELLER_COUNT_FOUR,
				AIR_COND_REQUIRED_TRUE);
	}

}
