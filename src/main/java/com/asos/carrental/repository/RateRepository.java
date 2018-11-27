package com.asos.carrental.repository;

import java.math.BigDecimal;

import com.asos.carrental.model.FuelType;

public interface RateRepository {

	BigDecimal findRateByFuelType(FuelType fuelType);

	BigDecimal findAdditionalTravellerRate();

}
