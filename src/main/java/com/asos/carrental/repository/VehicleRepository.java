package com.asos.carrental.repository;

import com.asos.carrental.model.FuelType;
import com.asos.carrental.model.VehicleSize;
import com.asos.carrental.model.VehicleType;

public interface VehicleRepository {

    VehicleSize fetchVehicleSize(VehicleType vehicleType);

    Integer fetchVehiclePassengerCapacity(VehicleType vehicleType);

    Boolean fectchAirCoditioningAvailibity(VehicleType vehicleType, String isAirConditioningRequired);

    FuelType fetchFuelType(VehicleType vehicleType, String fuelType);

}
