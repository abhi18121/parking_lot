package com.parkinglot.domain;

import java.util.Objects;

public class ParkingSlot {
    private int slotNumber;

    private String registrationNumber;
    private boolean isAvailable = true;
    public ParkingSlot(int slotNumber, String registrationNumber) {
        this.slotNumber = slotNumber;
        this.registrationNumber = registrationNumber;
        this.isAvailable = false;
    }

    public ParkingSlot(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot that = (ParkingSlot) o;
        return registrationNumber.equals(that.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }
}
