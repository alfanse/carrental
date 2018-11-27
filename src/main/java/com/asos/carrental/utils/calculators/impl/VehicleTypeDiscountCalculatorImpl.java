package com.asos.carrental.utils.calculators.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.asos.carrental.model.Vehicle;
import com.asos.carrental.repository.DiscountRepository;
import com.asos.carrental.utils.calculators.DiscountCalculator;

public class VehicleTypeDiscountCalculatorImpl implements DiscountCalculator {

	private final DiscountRepository discountRepository;

	public VehicleTypeDiscountCalculatorImpl(
			DiscountRepository discountRepository) {
		this.discountRepository = discountRepository;
	}

	@Override
	public BigDecimal calulateDiscount(BigDecimal standardRate,
			Vehicle vehicle) {
		Float discountFactor = discountRepository
				.findDiscountByVehicleType(vehicle.getVehicleType());

		if (discountFactor != null && discountFactor > 0) {
			return appliedDiscount(standardRate, discountFactor);
		}

		return standardRate;
	}

	private BigDecimal appliedDiscount(BigDecimal standardRate,
			Float discountFactor) {
		BigDecimal discountAmount = standardRate
				.multiply(new BigDecimal(discountFactor))
				.setScale(2, RoundingMode.HALF_UP);

		return standardRate.subtract(discountAmount).setScale(2,
				RoundingMode.HALF_UP);
	}

}
