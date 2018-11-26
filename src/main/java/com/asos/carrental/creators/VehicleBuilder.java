package com.asos.carrental.creators;

import com.asos.carrental.model.FuelType;
import com.asos.carrental.model.Vehicle;
import com.asos.carrental.model.VehicleSize;
import com.asos.carrental.model.VehicleType;

public class VehicleBuilder implements Builder<Vehicle> {

    private VehicleType vehicleType;
    private FuelType fuelType;
    private VehicleSize vehicleSize;
    private int maxPassengerCapacity;
    private Boolean isAirConditioned;

    public VehicleBuilder withVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public VehicleBuilder withFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public VehicleBuilder withVehicleSize(VehicleSize vehicleSize) {
        this.vehicleSize = vehicleSize;
        return this;
    }

    public VehicleBuilder withMaxPassengerCapacity(int maxPassengerCapacity) {
        this.maxPassengerCapacity = maxPassengerCapacity;
        return this;

    }

    public VehicleBuilder withIsAirConditioned(Boolean isAirConditioned) {
        this.isAirConditioned = isAirConditioned;
        return this;
    }

    @Override
    public Vehicle build() {
        Vehicle vehicle = new Vehicle();
        vehicle.setFuelType(fuelType);
        vehicle.setIsAirConditioned(isAirConditioned);
        vehicle.setMaxPassengerCapacity(maxPassengerCapacity);
        vehicle.setVehicleSize(vehicleSize);
        vehicle.setVehicleType(vehicleType);
        return vehicle;
    }

}
