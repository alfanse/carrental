package com.asos.carrental.model;

public enum FuelType {

    PETROL("Petrol"),
    DEISEL("Deisel");

    String fuelTypeName;

    FuelType(String fuelTypeName) {
        this.fuelTypeName = fuelTypeName;
    }

    public String getFuelTypeName() {
        return fuelTypeName;
    }

}
