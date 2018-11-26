package com.asos.carrental.repository.impl;

import java.util.HashMap;
import java.util.Map;

import com.asos.carrental.model.VehicleType;

public class VehiclePassengerCapacitiesDelegate {

    Map<VehicleType, Integer> vehiclePassengerCapacities = new HashMap<>();

    public VehiclePassengerCapacitiesDelegate() {
        vehiclePassengerCapacities.put(VehicleType.BUS, new Integer(20));
        vehiclePassengerCapacities.put(VehicleType.CAR, new Integer(4));
        vehiclePassengerCapacities.put(VehicleType.SUV, new Integer(8));
        vehiclePassengerCapacities.put(VehicleType.VAN, new Integer(6));
    }

    public Integer fetchVehiclePassengerCapacity(VehicleType vehicleType) {
        return vehiclePassengerCapacities.get(vehicleType);
    }

}
