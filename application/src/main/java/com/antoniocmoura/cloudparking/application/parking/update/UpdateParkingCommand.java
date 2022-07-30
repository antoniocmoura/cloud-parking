package com.antoniocmoura.cloudparking.application.parking.update;

public record UpdateParkingCommand(
        String id,
        String license,
        String state,
        String model,
        String color
) {
    public static UpdateParkingCommand with(
            final String id,
            final String license,
            final String state,
            final String model,
            final String color) {
        return new UpdateParkingCommand(
                id,
                license,
                state,
                model,
                color
        );
    }
}
