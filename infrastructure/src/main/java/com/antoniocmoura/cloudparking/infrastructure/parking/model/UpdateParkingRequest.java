package com.antoniocmoura.cloudparking.infrastructure.parking.model;

public record UpdateParkingRequest(
        String id,
        String license,
        String state,
        String model,
        String color
) {
}
