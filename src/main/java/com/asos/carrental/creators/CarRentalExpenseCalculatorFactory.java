package com.asos.carrental.creators;

import com.asos.carrental.expense.service.CarRentalExpenseCalculator;
import com.asos.carrental.expense.service.impl.CarRentalExpenseCalculatorImpl;
import com.asos.carrental.repository.DestinationRepository;
import com.asos.carrental.repository.DiscountRepository;
import com.asos.carrental.repository.VehicleRepository;
import com.asos.carrental.repository.impl.*;
import com.asos.carrental.utils.calculators.*;
import com.asos.carrental.utils.calculators.impl.*;

public class CarRentalExpenseCalculatorFactory {

	public static final CarRentalExpenseCalculator create() {

		VehicleDirector vehicleDirector = buildVehicleDirector();

		DistanceCalculator distanceCalculator = buildDistanceCalculator();

		RateRepositoryImpl rateRepository = new RateRepositoryImpl();
		RateCalculator finalRateCalculator = buildFinalRateCalculator(rateRepository);
		ExpenseCalculator expenseCalculator = buildExpenseCalculator(rateRepository);

		return new CarRentalExpenseCalculatorImpl(
				vehicleDirector,
				distanceCalculator,
				finalRateCalculator,
				expenseCalculator);
	}

	private static final ExpenseCalculator buildExpenseCalculator(RateRepositoryImpl rateRepository) {
		AdditionalExpenseCalculator additionalExpenseCalculator = buildAdditionalExpenseCalculator(rateRepository);
		return new StandardExpenseCalculatorImpl(additionalExpenseCalculator);
	}

	private static final AdditionalExpenseCalculator buildAdditionalExpenseCalculator(RateRepositoryImpl rateRepository) {
		return new AdditionalTravellerExpenseCalculatorImpl(rateRepository);
	}

	private static final RateCalculator buildFinalRateCalculator(RateRepositoryImpl rateRepository) {
		RateCalculator rateCalculator = buildRateCalculator(rateRepository);
		DiscountCalculator discountCalculator = buildDiscountCalculator();
		return new FinalRateCalculatorImpl(rateCalculator, discountCalculator);
	}

	private static final DiscountCalculator buildDiscountCalculator() {
		DiscountRepository discountRepository = new VehicleTypeDiscountRepositoryImpl();
		return new VehicleTypeDiscountCalculatorImpl(discountRepository);
	}

	private static final RateCalculator buildRateCalculator(RateRepositoryImpl rateRepository) {
		return new StandardRateCalculatorImpl(rateRepository);
	}

	private static final DistanceCalculator buildDistanceCalculator() {
		DestinationRepository destinationRepository = new StubbedDestinationRepositoryImpl();
		return new DistanceCalculatorImpl(destinationRepository);
	}

	private static final VehicleDirector buildVehicleDirector() {
		VehicleRepository vehicleRepository = buildVehicleRepository();
		return new VehicleDirector(vehicleRepository);
	}

	private static final VehicleRepository buildVehicleRepository() {

		AirConditionDelegate airConditionDecider = new AirConditionDelegate();
		FuelTypeDelegate fuelTypeDecider = new FuelTypeDelegate();
		VehiclePassengerCapacitiesDelegate vehiclePassengerCapacitiesDelegate = new VehiclePassengerCapacitiesDelegate();
		VehicleSizesDelegate vehicleSizesDelegate = new VehicleSizesDelegate();

		return new StubbedVehicleRepositoryImpl(
				airConditionDecider,
				fuelTypeDecider,
				vehiclePassengerCapacitiesDelegate,
				vehicleSizesDelegate);
	}

}
