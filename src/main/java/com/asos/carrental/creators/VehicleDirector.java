package com.asos.carrental.creators;

import com.asos.carrental.model.FuelType;
import com.asos.carrental.model.Vehicle;
import com.asos.carrental.model.VehicleSize;
import com.asos.carrental.model.VehicleType;
import com.asos.carrental.repository.VehicleRepository;
import com.asos.carrental.utils.TypeFetcher;

public class VehicleDirector {

    private VehicleRepository vehicleRepository;

    public VehicleDirector(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle buildVehicle(String vehicleType, String fuelType, String isAirConditioningRequired) {
        VehicleType selectedVehicleType = TypeFetcher.getType(VehicleType.class, vehicleType);
        return buildVehicle(selectedVehicleType, fuelType, isAirConditioningRequired);
    }

    public Vehicle buildVehicle(VehicleType vehicleType, String fuelType, String isAirConditioningRequired) {
        VehicleBuilder vehicleBuilder = new VehicleBuilder()
                .withVehicleType(vehicleType)
                .withFuelType(fetchFuelType(vehicleType, fuelType))
                .withIsAirConditioned(fectchAirCoditioningAvailibity(vehicleType, isAirConditioningRequired))
                .withMaxPassengerCapacity(fetchMaxPassengerCapacity(vehicleType))
                .withVehicleSize(fetchVehicleSize(vehicleType));

        return vehicleBuilder.build();
    }

    private Boolean fectchAirCoditioningAvailibity(VehicleType vehicleType, String isAirConditioningRequired) {
        return vehicleRepository.fectchAirCoditioningAvailibity(vehicleType, isAirConditioningRequired);
    }

    private VehicleSize fetchVehicleSize(VehicleType vehicleType) {
        return vehicleRepository.fetchVehicleSize(vehicleType);
    }

    private int fetchMaxPassengerCapacity(VehicleType vehicleType) {
        return vehicleRepository.fetchVehiclePassengerCapacity(vehicleType);
    }

    private FuelType fetchFuelType(VehicleType vehicleType, String fuelType) {
        return vehicleRepository.fetchFuelType(vehicleType, fuelType);
    }

}
