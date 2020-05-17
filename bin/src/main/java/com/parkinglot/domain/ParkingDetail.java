package com.parkinglot.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ParkingDetail {
    private Car car;

    public Car getCar() {
        return car;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    private ParkingSlot slot;
    private String message;

    public ParkingDetail(Car car, LocalDateTime parkedTime, ParkingSlot slot) {
        this.car = car;
        this.slot = slot;
    }

    public ParkingDetail() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
