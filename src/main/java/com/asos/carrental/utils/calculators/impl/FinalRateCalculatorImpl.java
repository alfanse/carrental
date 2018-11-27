package com.asos.carrental.utils.calculators.impl;

import java.math.BigDecimal;

import com.asos.carrental.model.Vehicle;
import com.asos.carrental.utils.calculators.DiscountCalculator;
import com.asos.carrental.utils.calculators.RateCalculator;

public class FinalRateCalculatorImpl implements RateCalculator {

	private final RateCalculator standardRateCalculator;
	private final DiscountCalculator discountCalculator;

	public FinalRateCalculatorImpl(RateCalculator rateCalculator,
			DiscountCalculator discountCalculator) {
		this.standardRateCalculator = rateCalculator;
		this.discountCalculator = discountCalculator;
	}

	@Override
	public BigDecimal calulateRate(Vehicle vehicle) {
		BigDecimal standardRate = standardRateCalculator.calulateRate(vehicle);
		BigDecimal discountedRate = discountCalculator
				.calulateDiscount(standardRate, vehicle);
		return discountedRate;
	}

}
