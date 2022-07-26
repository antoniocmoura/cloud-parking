package com.antoniocmoura.cloud.parking.application.parking;

import com.antoniocmoura.cloud.parking.domain.parking.Parking;

import java.time.LocalDateTime;

public record ParkingOutput(
        String id,
        String license,
        String state,
        String model,
        String color,
        LocalDateTime entryDate,
        LocalDateTime exitDate,
        Double bill
) {
    public static ParkingOutput fromDomain(Parking aParking) {
        return new ParkingOutput(
                aParking.getId().getValue(),
                aParking.getLicense(),
                aParking.getState(),
                aParking.getModel(),
                aParking.getColor(),
                aParking.getEntryDate(),
                aParking.getExitDate(),
                aParking.getBill()
        );
    }
}
