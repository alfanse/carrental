package com.asos.carrental.creators;

import com.asos.carrental.expense.service.CarRentalExpenseCalculator;
import com.asos.carrental.expense.service.impl.CarRentalExpenseCalculatorImpl;
import com.asos.carrental.repository.DestinationRepository;
import com.asos.carrental.repository.DiscountRepository;
import com.asos.carrental.repository.RateRepository;
import com.asos.carrental.repository.VehicleRepository;
import com.asos.carrental.repository.impl.AirConditionDelegate;
import com.asos.carrental.repository.impl.FuelTypeDelegate;
import com.asos.carrental.repository.impl.RateRepositoryImpl;
import com.asos.carrental.repository.impl.StubbedDestinationRepositoryImpl;
import com.asos.carrental.repository.impl.StubbedVehicleRepositoryImpl;
import com.asos.carrental.repository.impl.VehiclePassengerCapacitiesDelegate;
import com.asos.carrental.repository.impl.VehicleSizesDelegate;
import com.asos.carrental.repository.impl.VehicleTypeDiscountRepositoryImpl;
import com.asos.carrental.utils.calculators.AdditionalExpenseCalculator;
import com.asos.carrental.utils.calculators.DiscountCalculator;
import com.asos.carrental.utils.calculators.DistanceCalculator;
import com.asos.carrental.utils.calculators.ExpenseCalculator;
import com.asos.carrental.utils.calculators.RateCalculator;
import com.asos.carrental.utils.calculators.StandardExpenseCalculatorImpl;
import com.asos.carrental.utils.calculators.impl.AdditionalTravellerExpenseCalculatorImpl;
import com.asos.carrental.utils.calculators.impl.DistanceCalculatorImpl;
import com.asos.carrental.utils.calculators.impl.FinalRateCalculatorImpl;
import com.asos.carrental.utils.calculators.impl.StandardRateCalculatorImpl;
import com.asos.carrental.utils.calculators.impl.VehicleTypeDiscountCalculatorImpl;

public class CarRentalExpenseCalculatorFactory {

	public static final CarRentalExpenseCalculator create() {

		VehicleDirector vehicleDirector = buildVehicleDirector();
		DistanceCalculator distanceCalculator = buildDistanceCalculator();
		RateCalculator finalRateCalculator = buildFinalRateCalculator();
		ExpenseCalculator expenseCalculator = buildExpenseCalculator();

		CarRentalExpenseCalculator carRentalExpenseCalculator = new CarRentalExpenseCalculatorImpl(
				vehicleDirector, distanceCalculator, finalRateCalculator,
				expenseCalculator);

		return carRentalExpenseCalculator;
	}

	private static final ExpenseCalculator buildExpenseCalculator() {
		AdditionalExpenseCalculator additionalExpenseCalculator = buildAdditionalExpenseCalculator();
		ExpenseCalculator expenseCalculator = new StandardExpenseCalculatorImpl(
				additionalExpenseCalculator);
		return expenseCalculator;
	}

	private static final AdditionalExpenseCalculator buildAdditionalExpenseCalculator() {
		RateRepository rateRepository = new RateRepositoryImpl();
		AdditionalExpenseCalculator additionalExpenseCalculator = new AdditionalTravellerExpenseCalculatorImpl(
				rateRepository);
		return additionalExpenseCalculator;
	}

	private static final RateCalculator buildFinalRateCalculator() {
		RateCalculator rateCalculator = buildRateCalculator();
		DiscountCalculator discountCalculator = buildDiscountCalculator();
		RateCalculator finalRateCalculator = new FinalRateCalculatorImpl(
				rateCalculator, discountCalculator);
		return finalRateCalculator;
	}

	private static final DiscountCalculator buildDiscountCalculator() {
		DiscountRepository discountRepository = new VehicleTypeDiscountRepositoryImpl();
		DiscountCalculator discountCalculator = new VehicleTypeDiscountCalculatorImpl(
				discountRepository);
		return discountCalculator;
	}

	private static final RateCalculator buildRateCalculator() {
		RateRepository rateRepository = new RateRepositoryImpl();
		RateCalculator rateCalculator = new StandardRateCalculatorImpl(
				rateRepository);
		return rateCalculator;
	}

	private static final DistanceCalculator buildDistanceCalculator() {
		DestinationRepository destinationRepository = new StubbedDestinationRepositoryImpl();
		DistanceCalculator distanceCalculator = new DistanceCalculatorImpl(
				destinationRepository);
		return distanceCalculator;
	}

	private static final VehicleDirector buildVehicleDirector() {
		VehicleRepository vehicleRepository = buildVehicleRepository();
		VehicleDirector vehicleDirector = new VehicleDirector(
				vehicleRepository);
		return vehicleDirector;
	}

	private static final VehicleRepository buildVehicleRepository() {
		AirConditionDelegate airConditionDecider = new AirConditionDelegate();
		FuelTypeDelegate fuelTypeDecider = new FuelTypeDelegate();
		VehiclePassengerCapacitiesDelegate vehiclePassengerCapacitiesDelegate = new VehiclePassengerCapacitiesDelegate();
		VehicleSizesDelegate vehicleSizesDelegate = new VehicleSizesDelegate();
		VehicleRepository vehicleRepository = new StubbedVehicleRepositoryImpl(
				airConditionDecider, fuelTypeDecider,
				vehiclePassengerCapacitiesDelegate, vehicleSizesDelegate);
		return vehicleRepository;
	}

}
