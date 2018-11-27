package com.asos.carrental.utils.calculators.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.asos.carrental.model.Vehicle;
import com.asos.carrental.repository.RateRepository;
import com.asos.carrental.utils.calculators.RateCalculator;

public class StandardRateCalculatorImpl implements RateCalculator {

	private final RateRepository rateRepository;

	public StandardRateCalculatorImpl(RateRepository rateRepository) {
		this.rateRepository = rateRepository;
	}

	@Override
	public BigDecimal calulateRate(Vehicle vehicle) {
		return rateRepository.findRateByFuelType(vehicle.getFuelType())
				.setScale(2, RoundingMode.HALF_UP);
	}

}
