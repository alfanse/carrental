package com.asos.carrental.utils.calculators;

import java.math.BigDecimal;

import com.asos.carrental.model.Vehicle;

public interface DiscountCalculator {

	BigDecimal calulateDiscount(BigDecimal standardRate, Vehicle vehicle);

}
