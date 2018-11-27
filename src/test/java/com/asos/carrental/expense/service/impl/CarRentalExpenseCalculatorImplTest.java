package com.asos.carrental.expense.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.asos.carrental.creators.VehicleBuilder;
import com.asos.carrental.creators.VehicleDirector;
import com.asos.carrental.model.FuelType;
import com.asos.carrental.model.Vehicle;
import com.asos.carrental.model.VehicleSize;
import com.asos.carrental.model.VehicleType;
import com.asos.carrental.utils.calculators.DistanceCalculator;
import com.asos.carrental.utils.calculators.ExpenseCalculator;
import com.asos.carrental.utils.calculators.RateCalculator;

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

	CarRentalExpenseCalculatorImpl carRentalExpenseCalculatorImplUnderTest;

	String vehicleType;
	String fuelType;
	String destination;
	String tripType;
	String numberOfPeopleTravelling;
	String isAirConditioningRequired;
	private Vehicle swift;
	private Float distanceToMumbaiAndBack;
	private BigDecimal rateForSwift;
	private BigDecimal tripExpenseToMumbaiInASwift;

	@Before
	public void setup() {
		carRentalExpenseCalculatorImplUnderTest = new CarRentalExpenseCalculatorImpl(
				vehicleDirector, distanceCalculator, finalRateCalculator,
				expenseCalculator);

		vehicleType = VEHICLE_TYPE_CAR;
		fuelType = FUEL_TYPE_PETROL;
		destination = DESTINATION_MUMBAI;
		tripType = TRIP_TYPE_RETURN;
		numberOfPeopleTravelling = TRAVELLER_COUNT_FOUR;
		isAirConditioningRequired = AIR_COND_REQUIRED_TRUE;

		swift = new VehicleBuilder().withFuelType(FuelType.PETROL)
				.withVehicleType(VehicleType.CAR).withIsAirConditioned(true)
				.withMaxPassengerCapacity(4).withVehicleSize(VehicleSize.SMALL)
				.build();
		distanceToMumbaiAndBack = new Float("400");
		rateForSwift = new BigDecimal("15.00");
		tripExpenseToMumbaiInASwift = new BigDecimal("6000.00");
	}

	@Test
	public void shouldCalculateTotalExpense() {

		// given
		when(vehicleDirector.buildVehicle(vehicleType, fuelType,
				isAirConditioningRequired)).thenReturn(swift);
		when(distanceCalculator.getTotalDistance(destination, tripType))
				.thenReturn(distanceToMumbaiAndBack);
		when(finalRateCalculator.calulateRate(swift)).thenReturn(rateForSwift);
		when(expenseCalculator.calculateExpense(swift, numberOfPeopleTravelling,
				distanceToMumbaiAndBack, rateForSwift))
						.thenReturn(tripExpenseToMumbaiInASwift);

		// when
		BigDecimal calculatedExpense = carRentalExpenseCalculatorImplUnderTest
				.calculateExpense(vehicleType, fuelType, destination, tripType,
						numberOfPeopleTravelling, isAirConditioningRequired);

		// then
		BigDecimal assertedExpense = tripExpenseToMumbaiInASwift;
		assertThat(calculatedExpense, equalTo(assertedExpense));

	}

	@Test
	public void shouldVerifyVehicleBuilderParameters() {

		// given

		ArgumentCaptor<String> vehicleTypeCaptor = ArgumentCaptor
				.forClass(String.class);
		ArgumentCaptor<String> fuelTypeCaptor = ArgumentCaptor
				.forClass(String.class);
		ArgumentCaptor<String> airConRequiredCaptor = ArgumentCaptor
				.forClass(String.class);

		// when
		carRentalExpenseCalculatorImplUnderTest.calculateExpense(vehicleType,
				fuelType, destination, tripType, numberOfPeopleTravelling,
				isAirConditioningRequired);

		// then
		verify(vehicleDirector).buildVehicle(vehicleTypeCaptor.capture(),
				fuelTypeCaptor.capture(), airConRequiredCaptor.capture());

		assertThat(vehicleTypeCaptor.getValue(), equalTo(VEHICLE_TYPE_CAR));
		assertThat(fuelTypeCaptor.getValue(), equalTo(FUEL_TYPE_PETROL));
		assertThat(airConRequiredCaptor.getValue(),
				equalTo(AIR_COND_REQUIRED_TRUE));

	}

	@Test
	public void shouldVerifyDistanceCalculatorParameters() {
		// given
		ArgumentCaptor<String> destinationCaptor = ArgumentCaptor
				.forClass(String.class);
		ArgumentCaptor<String> tripTypeCaptor = ArgumentCaptor
				.forClass(String.class);

		// when
		carRentalExpenseCalculatorImplUnderTest.calculateExpense(vehicleType,
				fuelType, destination, tripType, numberOfPeopleTravelling,
				isAirConditioningRequired);

		// then

		verify(distanceCalculator).getTotalDistance(destinationCaptor.capture(),
				tripTypeCaptor.capture());

		assertThat(destinationCaptor.getValue(), equalTo(DESTINATION_MUMBAI));
		assertThat(tripTypeCaptor.getValue(), equalTo(TRIP_TYPE_RETURN));

	}

	@Test
	public void shouldVerifyRateCalculatorParameters() {
		// given
		ArgumentCaptor<Vehicle> vehicleCaptor = ArgumentCaptor
				.forClass(Vehicle.class);

		when(vehicleDirector.buildVehicle(vehicleType, fuelType,
				isAirConditioningRequired)).thenReturn(swift);

		// when
		carRentalExpenseCalculatorImplUnderTest.calculateExpense(vehicleType,
				fuelType, destination, tripType, numberOfPeopleTravelling,
				isAirConditioningRequired);

		// then

		verify(finalRateCalculator).calulateRate(vehicleCaptor.capture());
		assertThat(vehicleCaptor.getValue(), equalTo(swift));

	}

	@Test
	public void shouldVerifyExpenseCalculatorParameters() {
		// given
		ArgumentCaptor<Vehicle> vehicleCaptor = ArgumentCaptor
				.forClass(Vehicle.class);
		ArgumentCaptor<String> travellerCountCaptor = ArgumentCaptor
				.forClass(String.class);
		ArgumentCaptor<Float> distanceCaptor = ArgumentCaptor
				.forClass(Float.class);
		ArgumentCaptor<BigDecimal> vehicleRateCaptor = ArgumentCaptor
				.forClass(BigDecimal.class);

		when(vehicleDirector.buildVehicle(vehicleType, fuelType,
				isAirConditioningRequired)).thenReturn(swift);
		when(distanceCalculator.getTotalDistance(destination, tripType))
				.thenReturn(distanceToMumbaiAndBack);
		when(finalRateCalculator.calulateRate(swift)).thenReturn(rateForSwift);

		// when
		carRentalExpenseCalculatorImplUnderTest.calculateExpense(vehicleType,
				fuelType, destination, tripType, numberOfPeopleTravelling,
				isAirConditioningRequired);

		// then

		verify(expenseCalculator).calculateExpense(vehicleCaptor.capture(),
				travellerCountCaptor.capture(), distanceCaptor.capture(),
				vehicleRateCaptor.capture());

		assertThat(vehicleCaptor.getValue(), equalTo(swift));
		assertThat(travellerCountCaptor.getValue(),
				equalTo(TRAVELLER_COUNT_FOUR));
		assertThat(distanceCaptor.getValue(), equalTo(distanceToMumbaiAndBack));
		assertThat(vehicleRateCaptor.getValue(), equalTo(rateForSwift));

	}

}
