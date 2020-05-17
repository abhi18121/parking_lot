package com.parkinglot.charges;

public class ParkingCharges {

    public static int charge(int hours) {

        int charges = 0;

        for (int i = 0; i <= hours; i++) {
            if (i <= 2) {
                charges = 10;
                i = 2;
            } else {
                charges = charges + (10 * (hours - 2));
                i = hours;
            }
        }

        return charges;
    }
}
