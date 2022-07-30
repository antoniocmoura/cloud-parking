package com.antoniocmoura.cloudparking.infrastructure.parking.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ParkingResponse(
        String id,
        String license,
        String state,
        String model,
        String color,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime entryDate,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime exitDate,
        Double bill
) {
}
