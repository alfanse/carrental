package com.asos.carrental.repository.impl;

import java.util.HashMap;
import java.util.Map;

import com.asos.carrental.model.VehicleType;
import com.asos.carrental.repository.DiscountRepository;

public class VehicleTypeDiscountRepositoryImpl implements DiscountRepository {

	private final Map<VehicleType, Float> vehicleTypeDiscounts = new HashMap<>();

	public VehicleTypeDiscountRepositoryImpl() {
		vehicleTypeDiscounts.put(VehicleType.BUS, new Float("0.02"));
	}

	@Override
	public Float findDiscountByVehicleType(VehicleType vehicleType) {
		return vehicleTypeDiscounts.get(vehicleType);
	}

}
