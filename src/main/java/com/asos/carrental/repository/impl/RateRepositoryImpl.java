package com.asos.carrental.repository.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.asos.carrental.model.FuelType;
import com.asos.carrental.repository.RateRepository;

public class RateRepositoryImpl implements RateRepository {

	Map<FuelType, BigDecimal> standardRatesByFuelType = new HashMap<>();
	private static final BigDecimal ADDITIONAL_TRAVELLER_RATE = new BigDecimal(
			"1.00");

	public RateRepositoryImpl() {
		standardRatesByFuelType.put(FuelType.PETROL, new BigDecimal("15.00"));
		standardRatesByFuelType.put(FuelType.DEISEL,
				new BigDecimal("15.00").subtract(new BigDecimal("1.00")));
	}

	@Override
	public BigDecimal findRateByFuelType(FuelType fuelType) {
		return standardRatesByFuelType.get(fuelType);
	}

	@Override
	public BigDecimal findAdditionalTravellerRate() {
		return ADDITIONAL_TRAVELLER_RATE;
	}

}
