package com.asos.carrental.creators;

import com.asos.carrental.model.FuelType;
import com.asos.carrental.model.Vehicle;
import com.asos.carrental.model.VehicleSize;
import com.asos.carrental.model.VehicleType;
import com.asos.carrental.repository.VehicleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleDirectorTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    VehicleDirector vehicleDirector;

    private static final String VEHICLE_BUS = "bus";
    private static final String FUEL_TYPE_DEISEL = "DEISEL";
    private static final String AC_REQUIRED = "true";
    private static final VehicleType VEHICLE_TYPE_BUS = VehicleType.BUS;

    @Test
    public void shouldBuildVehilcleWithFuelType() {

        //given
        givenABus();

        //when
        Vehicle builtVehicle = vehicleDirector.buildVehicle(VEHICLE_BUS, FUEL_TYPE_DEISEL, AC_REQUIRED);

        //then
        assertThat(builtVehicle.getFuelType(), equalTo(FuelType.DEISEL));

    }

    @Test
    public void shouldBuildVehilcleWithAirCon() {

        //given
        givenABus();

        //when
        Vehicle builtVehicle = vehicleDirector.buildVehicle(VEHICLE_BUS, FUEL_TYPE_DEISEL, AC_REQUIRED);

        //then
        assertThat(builtVehicle.getIsAirConditioned(), equalTo(true));

    }

    @Test
    public void shouldBuildVehilcleWithMaxPassengerCapacity() {

        //given
        givenABus();

        //when
        Vehicle builtVehicle = vehicleDirector.buildVehicle(VEHICLE_BUS, FUEL_TYPE_DEISEL, AC_REQUIRED);

        //then
        assertThat(builtVehicle.getMaxPassengerCapacity(), equalTo(20));

    }

    @Test
    public void shouldBuildVehilcleWithVehicleSize() {

        //given
        givenABus();

        //when
        Vehicle builtVehicle = vehicleDirector.buildVehicle(VEHICLE_BUS, FUEL_TYPE_DEISEL, AC_REQUIRED);

        //then
        assertThat(builtVehicle.getVehicleSize(), equalTo(VehicleSize.LARGE));

    }

    @Test
    public void shouldBuildVehilcleWithVehicleType() {

        //given
        givenABus();

        //when
        Vehicle builtVehicle = vehicleDirector.buildVehicle(VEHICLE_BUS, FUEL_TYPE_DEISEL, AC_REQUIRED);

        //then
        assertThat(builtVehicle.getVehicleType(), equalTo(VehicleType.BUS));

    }

    private void givenABus() {
        when(vehicleRepository.fectchAirCoditioningAvailibity(VehicleDirectorTest.VEHICLE_TYPE_BUS, VehicleDirectorTest.AC_REQUIRED))
                .thenReturn(Boolean.TRUE);
        when(vehicleRepository.fetchFuelType(VehicleDirectorTest.VEHICLE_TYPE_BUS, VehicleDirectorTest.FUEL_TYPE_DEISEL)).thenReturn(FuelType.DEISEL);
        when(vehicleRepository.fetchVehiclePassengerCapacity(VehicleDirectorTest.VEHICLE_TYPE_BUS)).thenReturn(20);
        when(vehicleRepository.fetchVehicleSize(VehicleDirectorTest.VEHICLE_TYPE_BUS)).thenReturn(VehicleSize.LARGE);
    }

}
