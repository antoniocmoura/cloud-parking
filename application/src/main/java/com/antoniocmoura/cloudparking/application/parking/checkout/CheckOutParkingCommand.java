package com.antoniocmoura.cloudparking.application.parking.checkout;

public record CheckOutParkingCommand(
        String id
) {
    public static CheckOutParkingCommand with(String id) {
        return new CheckOutParkingCommand(id);
    }
}
