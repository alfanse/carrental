package com.asos.carrental.repository.impl;

import com.asos.carrental.model.FuelType;
import com.asos.carrental.model.VehicleSize;
import com.asos.carrental.model.VehicleType;
import com.asos.carrental.repository.VehicleRepository;

public class StubbedVehicleRepositoryImpl implements VehicleRepository {

    private AirConditionDelegate airConditionDecider;
    private FuelTypeDelegate fuelTypeDecider;
    private VehiclePassengerCapacitiesDelegate vehiclePassengerCapacitiesDelegate;
    private VehicleSizesDelegate vehicleSizesDelegate;

    public StubbedVehicleRepositoryImpl(AirConditionDelegate airConditionDecider, FuelTypeDelegate fuelTypeDecider,
            VehiclePassengerCapacitiesDelegate vehiclePassengerCapacitiesDelegate,
            VehicleSizesDelegate vehicleSizesDelegate) {
        this.airConditionDecider = airConditionDecider;
        this.fuelTypeDecider = fuelTypeDecider;
        this.vehiclePassengerCapacitiesDelegate = vehiclePassengerCapacitiesDelegate;
        this.vehicleSizesDelegate = vehicleSizesDelegate;
    }

    @Override
    public VehicleSize fetchVehicleSize(VehicleType vehicleType) {
        return vehicleSizesDelegate.fetchVehicleSize(vehicleType);
    }

    @Override
    public Integer fetchVehiclePassengerCapacity(VehicleType vehicleType) {
        return vehiclePassengerCapacitiesDelegate.fetchVehiclePassengerCapacity(vehicleType);
    }

    @Override
    public FuelType fetchFuelType(VehicleType vehicleType, String fuelType) {
        VehicleSize vehicleSize = fetchVehicleSize(vehicleType);
        return fuelTypeDecider.fetchFuelType(vehicleSize, fuelType);
    }

    @Override
    public Boolean fectchAirCoditioningAvailibity(VehicleType vehicleType, String isAirConditioningRequired) {
        return airConditionDecider.fectchAirCoditioningAvailibity(vehicleType, isAirConditioningRequired);
    }

}
