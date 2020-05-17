package com.parkinglot.domain;

public class Car {
    private String registrationNumber;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Car(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
