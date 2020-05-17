package com.parkinglot;

import com.parkinglot.domain.Command;
import com.parkinglot.domain.ParkingLot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static final String REGEX = " ";

    public static void main(String[] args) {
        System.out.println("Set up has been executed successfully");
        readCommandsFromFile(args[0]);
    }

    private static void readCommandsFromFile(String arg) {
        File inputFile = new File(arg);
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            ParkingLot parkingLot = null;
            while ((line = br.readLine()) != null) {
                String[] parkingDetail = line.split(REGEX);
                switch (getCommand(parkingDetail[0])) {
                    case PARK:
                        parkingLot.park(parkingDetail[1]);
                        break;
                    case LEAVE:
                        parkingLot.leave(parkingDetail[1], Integer.parseInt(parkingDetail[2]));
                        break;
                    case STATUS:
                        parkingLot.status();
                        break;
                    case CREATE_LOT:
                        parkingLot = new ParkingLot(Integer.parseInt(parkingDetail[1]));
                        break;
                    default:
                        ;
                }
            }
        } catch (
                IOException e) {
            System.out.println("Exception while parsing file in try block =>" + e.getMessage());
        }
    }

    private static Command getCommand(String name) {
        return Command.getCommand(name).get();
    }

}
