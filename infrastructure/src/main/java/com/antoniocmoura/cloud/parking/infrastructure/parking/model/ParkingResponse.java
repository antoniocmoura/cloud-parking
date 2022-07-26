package com.antoniocmoura.cloud.parking.infrastructure.parking.model;

import java.time.LocalDateTime;

public record ParkingResponse(
        String id,
        String license,
        String state,
        String model,
        String color,
        LocalDateTime entryDate,
        LocalDateTime exitDate,
        Double bill
) {
}
