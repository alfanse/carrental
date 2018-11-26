package com.asos.carrental.repository.impl;

import com.asos.carrental.model.FuelType;
import com.asos.carrental.model.VehicleSize;
import com.asos.carrental.utils.TypeFetcher;

public class FuelTypeDelegate {

    public FuelType fetchFuelType(VehicleSize vehicleSize, String fuelType) {

        if (vehicleSize == VehicleSize.LARGE) {
            return FuelType.DEISEL;
        } else {
            return TypeFetcher.getType(FuelType.class, fuelType);
        }

    }

}
