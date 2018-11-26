package com.asos.carrental.utils.calculators.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.asos.carrental.repository.DestinationRepository;

@RunWith(MockitoJUnitRunner.class)
public class DistanceCalculatorImplTest {

    DistanceCalculatorImpl distanceCalculatorImplUnderTest;

    @Mock
    private DestinationRepository destinationRepository;

    @Before
    public void setup() {
        distanceCalculatorImplUnderTest = new DistanceCalculatorImpl(destinationRepository);
    }

    @Test
    public void shouldDoubleDistanceForRETURNTrip() {
        //given        
        String destination = "Chennai";
        String tripTypeName = "return";
        String distanceToDestination = "1234.5";
        when(destinationRepository.getDestinationDistance(destination)).thenReturn(new Float(distanceToDestination));
        //when        
        Float totalDistance = distanceCalculatorImplUnderTest.getTotalDistance(destination, tripTypeName);
        //then

        float doubledDistanceToDestination = 2469F;
        assertThat(totalDistance, equalTo(doubledDistanceToDestination));
    }

    @Test
    public void shouldBeSameAsDestinationDistanceForSINGLETrip() {
        //given        
        String destination = "Kolkata";
        String tripTypeName = "single";
        String distanceToDestination = "1800";
        when(destinationRepository.getDestinationDistance(destination)).thenReturn(new Float(distanceToDestination));
        //when        
        Float totalDistance = distanceCalculatorImplUnderTest.getTotalDistance(destination, tripTypeName);
        //then

        assertThat(totalDistance, equalTo(new Float(distanceToDestination)));
    }

}
