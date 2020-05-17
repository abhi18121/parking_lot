package com.parkinglot.charges;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ParkingChargesTest {

    @Test
    public void shouldChargeForParking() {
        //given
        ParkingCharges parkingCharges = new ParkingCharges();
        //when
        int charge = parkingCharges.charge(6);
        //then
        Assert.assertEquals(50, charge);
    }

    @Test
    public void shouldChargeForParkingForLessThanTwoHours() {
        //given
        ParkingCharges parkingCharges = new ParkingCharges();
        //when
        int charge = parkingCharges.charge(1);
        //then
        Assert.assertEquals(10, charge);
    }
}