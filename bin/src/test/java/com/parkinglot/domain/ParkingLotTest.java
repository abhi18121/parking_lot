package com.parkinglot.domain;

import com.parkinglot.exception.ZeroOrLessParkingSlotCapacityException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static com.parkinglot.exception.ZeroOrLessParkingSlotCapacityException.MESSAGE;

//given
//when
//then
public class ParkingLotTest {

    @Test
    public void shouldNotCreateParkingLotForLessThanZeroCapacity() {
        //given
        int capacity = 0;
        //when
        try {
            new ParkingLot(capacity);
        } catch (ZeroOrLessParkingSlotCapacityException e) {
            //then
            Assert.assertEquals(MESSAGE, e.getMessage());
        }
    }


    @Test
    public void shouldCreateParkingLotWithGivenCapacity() {
        //given
        int capacity = 6;
        //when
        ParkingLot parkingLot = new ParkingLot(capacity);
        //then
        Assert.assertEquals(capacity, parkingLot.getCapacity());
    }

    @Test
    public void shouldGiveSorryParkingLotIsFullMessageWhenParkingIsFull() {
        //given
        int capacity = 6;
        //when
        ParkingLot parkingLot = new ParkingLot(capacity);
        parkingLot.park("1-AB");
        parkingLot.park("2-AB");
        parkingLot.park("3-AB");
        parkingLot.park("4-AB");
        parkingLot.park("5-AB");
        parkingLot.park("6-AB");
        ParkingDetail parkingDetail = parkingLot.park("7-AB");
        //then
        Assert.assertEquals(ParkingLot.SORRY_PARKING_LOT_IS_FULL, parkingDetail.getMessage());
    }

    @Test
    public void shouldGiveSorryVehicleIsAlreadyParkedMessageWhenVehicleIsAlreadyParked() {
        //given
        int capacity = 6;
        //when
        ParkingLot parkingLot = new ParkingLot(capacity);
        parkingLot.park("1-AB");
        ParkingDetail parkingDetail = parkingLot.park("1-AB");
        //then
        Assert.assertEquals(ParkingLot.SORRY_VEHICLE_IS_ALREADY_PARKED, parkingDetail.getMessage());
    }

    @Test
    public void shouldParkVehicle() {
        //given
        int capacity = 6;
        //when
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingDetail parkingDetail = parkingLot.park("1-AB");
        //then
        int slotNumber = parkingDetail.getSlot().getSlotNumber();
        Assert.assertEquals(ParkingLot.ALLOCATED_SLOT_NUMBER + slotNumber, parkingDetail.getMessage());
        Assert.assertEquals(parkingDetail.getCar().getRegistrationNumber(), "1-AB");
    }

    @Test
    public void shouldNotLeaveInvalidCar() {
        //given
        int capacity = 6;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingDetail parkingDetail = parkingLot.park("1-AB");
        //when
        ParkingDetail leftVehicleParkingDetail = parkingLot.leave("1-A", 4);
        //then
        Assert.assertEquals("Registration number 1-A not found ", leftVehicleParkingDetail.getMessage());
    }

    @Test
    public void shouldLeaveParking() {
        //given
        int capacity = 6;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingDetail parkingDetail = parkingLot.park("1-AB");
        //when
        ParkingDetail leftVehicleParkingDetail = parkingLot.leave("1-AB", 4);
        //then
        Assert.assertNotNull(leftVehicleParkingDetail.getMessage());
        Assert.assertNotNull(parkingDetail.getCar().getRegistrationNumber(), leftVehicleParkingDetail.getCar().getRegistrationNumber());
    }

    @Test
    public void shouldGetParkingLotStatus() {
        //given
        int capacity = 6;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingDetail parkingDetail = parkingLot.park("1-AB");
        //when
        Map<ParkingSlot, ParkingDetail> status = parkingLot.status();
        //then
        Assert.assertEquals(1, status.size());
    }

}