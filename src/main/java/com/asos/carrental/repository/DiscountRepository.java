package com.asos.carrental.repository;

import com.asos.carrental.model.VehicleType;

public interface DiscountRepository {

	Float findDiscountByVehicleType(VehicleType vehicleType);
}
