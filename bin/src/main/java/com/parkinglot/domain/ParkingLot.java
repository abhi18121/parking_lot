package com.parkinglot.domain;

import com.parkinglot.domain.Car;
import com.parkinglot.domain.NearestToEntryParking;
import com.parkinglot.domain.ParkingDetail;
import com.parkinglot.domain.ParkingSlot;
import com.parkinglot.exception.ZeroOrLessParkingSlotCapacityException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingLot {
    public static final String SORRY_VEHICLE_IS_ALREADY_PARKED = "Sorry, vehicle is already parked.";
    public static final String SORRY_PARKING_LOT_IS_FULL = "Sorry, parking lot is full";
    public static final String ALLOCATED_SLOT_NUMBER = "Allocated slot number: ";
    private int capacity;
    private Map<ParkingSlot, ParkingDetail> parkingDetails = new HashMap<>();
    private AtomicInteger numberOfSlots;
    private NearestToEntryParking nearestToEntryParking;

    public ParkingLot(int capacity) {
        if (capacity <= 0) {
            throw new ZeroOrLessParkingSlotCapacityException();
        }
        this.capacity = capacity;
        this.numberOfSlots = new AtomicInteger(capacity);
        nearestToEntryParking = new NearestToEntryParking(capacity);
        System.out.println("Created parking lot with " + capacity + " slots");
    }

    int getCapacity() {
        return capacity;
    }

    public ParkingDetail park(String registrationNumber) {
        ParkingDetail parkingDetail = new ParkingDetail();
        if (isCarAlreadyParked(registrationNumber)) {
            System.out.println(SORRY_VEHICLE_IS_ALREADY_PARKED);
            parkingDetail.setMessage(SORRY_VEHICLE_IS_ALREADY_PARKED);
        } else if (isParkingAvailable()) {
            System.out.println(SORRY_PARKING_LOT_IS_FULL);
            parkingDetail.setMessage(SORRY_PARKING_LOT_IS_FULL);
        } else {
            parkingDetail = getParkingDetail(registrationNumber);
        }
        return parkingDetail;
    }

    private ParkingDetail getParkingDetail(String registrationNumber) {
        ParkingDetail parkingDetail;
        int slot = nearestToEntryParking.getSlot();
        ParkingSlot parkingSlot = new ParkingSlot(slot, registrationNumber);
        parkingDetail = new ParkingDetail(new Car(registrationNumber), LocalDate.now(), parkingSlot);
        this.parkingDetails.put(new ParkingSlot(slot, registrationNumber), parkingDetail);
        numberOfSlots.decrementAndGet();
        System.out.println(ALLOCATED_SLOT_NUMBER + slot);
        parkingDetail.setMessage(ALLOCATED_SLOT_NUMBER + slot);
        return parkingDetail;
    }

    private boolean isCarAlreadyParked(String registrationNumber) {
        return this.parkingDetails
                .keySet()
                .stream()
                .filter(parkingSlot -> registrationNumber.equals(parkingSlot.getRegistrationNumber()))
                .findAny().isPresent();
    }

    public ParkingDetail leave(String registrationNumber, int hours) {
        ParkingSlot parkingSlot = new ParkingSlot(registrationNumber);
        ParkingDetail parkingDetail = new ParkingDetail();
        if (parkingDetails.containsKey(parkingSlot)) {
            parkingDetail = parkingDetails.remove(parkingSlot);
            int slotNumber = parkingDetail.getSlot().getSlotNumber();
            nearestToEntryParking.removeSlot(slotNumber);
            String message = "Registration number " + registrationNumber + " with Slot Number "
                    + slotNumber + " is free with Charge ";
            System.out.println(message);
            parkingDetail.setMessage(message);
        }
        return parkingDetail;
    }

    public void status() {
    }

    public boolean isParkingAvailable() {
        return numberOfSlots.get() < 1;
    }

}
