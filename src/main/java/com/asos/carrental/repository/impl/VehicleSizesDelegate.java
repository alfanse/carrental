package com.asos.carrental.repository.impl;

import java.util.HashMap;
import java.util.Map;

import com.asos.carrental.model.VehicleSize;
import com.asos.carrental.model.VehicleType;

public class VehicleSizesDelegate {

    Map<VehicleType, VehicleSize> vehicleSizes = new HashMap<>();

    public VehicleSizesDelegate() {
        vehicleSizes.put(VehicleType.CAR, VehicleSize.SMALL);
        vehicleSizes.put(VehicleType.BUS, VehicleSize.LARGE);
        vehicleSizes.put(VehicleType.SUV, VehicleSize.LARGE);
        vehicleSizes.put(VehicleType.VAN, VehicleSize.LARGE);
    }

    public VehicleSize fetchVehicleSize(VehicleType vehicleType) {
        return vehicleSizes.get(vehicleType);
    }

}
