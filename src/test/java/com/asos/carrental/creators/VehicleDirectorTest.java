package com.asos.carrental.creators;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.asos.carrental.model.FuelType;
import com.asos.carrental.model.Vehicle;
import com.asos.carrental.model.VehicleSize;
import com.asos.carrental.model.VehicleType;
import com.asos.carrental.repository.VehicleRepository;

@RunWith(MockitoJUnitRunner.class)
public class VehicleDirectorTest {

    @Mock
    private VehicleRepository vehicleRepository;

    VehicleDirector vehicleDirector;

    @Before
    public void setup() {
        vehicleDirector = new VehicleDirector(vehicleRepository);

    }

    @Test
    public void shouldBuildVehilcleWithFuelType() {

        //given
        String vehicleTypeName = "bus";
        String fuelType = "DEISEL";
        String isAirConditioningRequired = "true";
        VehicleType vehicleType = VehicleType.BUS;
        when(vehicleRepository.fectchAirCoditioningAvailibity(vehicleType, isAirConditioningRequired))
                .thenReturn(new Boolean(true));
        when(vehicleRepository.fetchFuelType(vehicleType, fuelType)).thenReturn(FuelType.DEISEL);
        when(vehicleRepository.fetchVehiclePassengerCapacity(vehicleType)).thenReturn(20);
        when(vehicleRepository.fetchVehicleSize(vehicleType)).thenReturn(VehicleSize.LARGE);

        //when
        Vehicle builtVehicle = vehicleDirector.buildVehicle(vehicleTypeName, fuelType, isAirConditioningRequired);

        //then
        assertThat(builtVehicle.getFuelType(), equalTo(FuelType.DEISEL));

    }

    @Test
    public void shouldBuildVehilcleWithAirCon() {

        //given
        String vehicleTypeName = "bus";
        String fuelType = "DEISEL";
        String isAirConditioningRequired = "true";
        VehicleType vehicleType = VehicleType.BUS;
        when(vehicleRepository.fectchAirCoditioningAvailibity(vehicleType, isAirConditioningRequired))
                .thenReturn(new Boolean(true));
        when(vehicleRepository.fetchFuelType(vehicleType, fuelType)).thenReturn(FuelType.DEISEL);
        when(vehicleRepository.fetchVehiclePassengerCapacity(vehicleType)).thenReturn(20);
        when(vehicleRepository.fetchVehicleSize(vehicleType)).thenReturn(VehicleSize.LARGE);

        //when
        Vehicle builtVehicle = vehicleDirector.buildVehicle(vehicleTypeName, fuelType, isAirConditioningRequired);

        //then
        assertThat(builtVehicle.getIsAirConditioned(), equalTo(true));

    }

    @Test
    public void shouldBuildVehilcleWithMaxPassengerCapacity() {

        //given
        String vehicleTypeName = "bus";
        String fuelType = "DEISEL";
        String isAirConditioningRequired = "true";
        VehicleType vehicleType = VehicleType.BUS;
        when(vehicleRepository.fectchAirCoditioningAvailibity(vehicleType, isAirConditioningRequired))
                .thenReturn(new Boolean(true));
        when(vehicleRepository.fetchFuelType(vehicleType, fuelType)).thenReturn(FuelType.DEISEL);
        when(vehicleRepository.fetchVehiclePassengerCapacity(vehicleType)).thenReturn(20);
        when(vehicleRepository.fetchVehicleSize(vehicleType)).thenReturn(VehicleSize.LARGE);

        //when
        Vehicle builtVehicle = vehicleDirector.buildVehicle(vehicleTypeName, fuelType, isAirConditioningRequired);

        //then
        assertThat(builtVehicle.getMaxPassengerCapacity(), equalTo(20));

    }

    @Test
    public void shouldBuildVehilcleWithVehicleSize() {

        //given
        String vehicleTypeName = "bus";
        String fuelType = "DEISEL";
        String isAirConditioningRequired = "true";
        VehicleType vehicleType = VehicleType.BUS;
        when(vehicleRepository.fectchAirCoditioningAvailibity(vehicleType, isAirConditioningRequired))
                .thenReturn(new Boolean(true));
        when(vehicleRepository.fetchFuelType(vehicleType, fuelType)).thenReturn(FuelType.DEISEL);
        when(vehicleRepository.fetchVehiclePassengerCapacity(vehicleType)).thenReturn(20);
        when(vehicleRepository.fetchVehicleSize(vehicleType)).thenReturn(VehicleSize.LARGE);

        //when
        Vehicle builtVehicle = vehicleDirector.buildVehicle(vehicleTypeName, fuelType, isAirConditioningRequired);

        //then
        assertThat(builtVehicle.getVehicleSize(), equalTo(VehicleSize.LARGE));

    }

    @Test
    public void shouldBuildVehilcleWithVehicleType() {

        //given
        String vehicleTypeName = "bus";
        String fuelType = "DEISEL";
        String isAirConditioningRequired = "true";
        VehicleType vehicleType = VehicleType.BUS;
        when(vehicleRepository.fectchAirCoditioningAvailibity(vehicleType, isAirConditioningRequired))
                .thenReturn(new Boolean(true));
        when(vehicleRepository.fetchFuelType(vehicleType, fuelType)).thenReturn(FuelType.DEISEL);
        when(vehicleRepository.fetchVehiclePassengerCapacity(vehicleType)).thenReturn(20);
        when(vehicleRepository.fetchVehicleSize(vehicleType)).thenReturn(VehicleSize.LARGE);

        //when
        Vehicle builtVehicle = vehicleDirector.buildVehicle(vehicleTypeName, fuelType, isAirConditioningRequired);

        //then
        assertThat(builtVehicle.getVehicleType(), equalTo(VehicleType.BUS));

    }

}
