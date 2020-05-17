package com.parkinglot;

import java.time.LocalDate;

public class ParkingDetail {
    private Car car;
    private LocalDate parkedTime;

    public Car getCar() {
        return car;
    }

    public LocalDate getParkedTime() {
        return parkedTime;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    private ParkingSlot slot;
    private String message;

    public ParkingDetail(Car car, LocalDate parkedTime, ParkingSlot slot) {
        this.car = car;
        this.parkedTime = parkedTime;
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
