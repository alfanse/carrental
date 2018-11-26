package com.asos.carrental.model;

public enum VehicleType {

    BUS("Bus"),
    CAR("Car"),
    SUV("SUV"),
    VAN("Van");

    String vehicleTypeName;

    VehicleType(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

}
