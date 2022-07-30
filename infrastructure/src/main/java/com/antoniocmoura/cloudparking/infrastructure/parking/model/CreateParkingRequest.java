package com.antoniocmoura.cloudparking.infrastructure.parking.model;

public record CreateParkingRequest(
         String license,
         String state,
         String model,
         String color
) {
}
