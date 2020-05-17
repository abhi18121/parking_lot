package com.parkinglot;

public class ZeroOrLessParkingSlotCapacityException extends RuntimeException {
    public static final String MESSAGE = "Parking capacity should not be less than or equal to zero";

    public ZeroOrLessParkingSlotCapacityException() {
        super(MESSAGE);
    }
}
