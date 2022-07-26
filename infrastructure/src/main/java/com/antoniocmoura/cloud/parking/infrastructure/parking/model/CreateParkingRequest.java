package com.antoniocmoura.cloud.parking.infrastructure.parking.model;

public record CreateParkingRequest(
         String license,
         String state,
         String model,
         String color
) {
}
