package com.asos.carrental.repository.impl;

import com.asos.carrental.model.VehicleType;

public class AirConditionDelegate {

    public Boolean fectchAirCoditioningAvailibity(VehicleType vehicleType, String isAirConditioningRequired) {
        if (vehicleType == VehicleType.SUV) {
            return new Boolean(true);
        } else {
            return new Boolean(isAirConditioningRequired);
        }
    }

}
