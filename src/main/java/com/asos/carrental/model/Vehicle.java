package com.asos.carrental.model;

public class Vehicle {

    private VehicleType vehicleType;
    private FuelType fuelType;
    private VehicleSize vehicleSize;
    private int maxPassengerCapacity;
    private Boolean isAirConditioned;

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public VehicleSize getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(VehicleSize vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public int getMaxPassengerCapacity() {
        return maxPassengerCapacity;
    }

    public void setMaxPassengerCapacity(int maxPassengerCapacity) {
        this.maxPassengerCapacity = maxPassengerCapacity;
    }

    public Boolean getIsAirConditioned() {
        return isAirConditioned;
    }

    public void setIsAirConditioned(Boolean isAirConditioned) {
        this.isAirConditioned = isAirConditioned;
    }

}
