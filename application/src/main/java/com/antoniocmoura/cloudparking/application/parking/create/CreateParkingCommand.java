package com.antoniocmoura.cloudparking.application.parking.create;

public record CreateParkingCommand (
        String license,
        String state,
        String model,
        String color
) {
    public static CreateParkingCommand with(
            final String license,
            final String state,
            final String model,
            final String color
    ) {
        return new CreateParkingCommand(
                license,
                state,
                model,
                color
        );
    }
}
