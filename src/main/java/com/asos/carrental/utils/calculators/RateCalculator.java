package com.asos.carrental.utils.calculators;

import java.math.BigDecimal;

import com.asos.carrental.model.Vehicle;

public interface RateCalculator {

    BigDecimal getCalulateRate(Vehicle vehicle);

}
