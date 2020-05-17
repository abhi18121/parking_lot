package com.parkinglot.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Command {

    CREATE_LOT("create_parking_lot"),
    PARK("park"),
    LEAVE("leave"),
    STATUS("status");

    private static Map<String, Command> commands = new HashMap<>();

    private final String command;

    Command(final String text) {
        this.command = text;
    }

    @Override
    public String toString() {
        return command;
    }

    public static Optional<Command> getCommand(String text) {
        return Arrays.stream(values())
                .filter(bl -> bl.command.equalsIgnoreCase(text))
                .findFirst();
    }

}
