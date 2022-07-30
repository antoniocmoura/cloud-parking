package com.antoniocmoura.cloudparking.domain.parking;

import com.antoniocmoura.cloudparking.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class ParkingID extends Identifier {

    private final String value;

    public ParkingID(final String value) {
        this.value = value;
    }

    public static ParkingID unique() {
        return ParkingID.from(UUID.randomUUID());
    }

    public static ParkingID from(final String anId) {
        return new ParkingID(anId);
    }

    public static ParkingID from(final UUID anId) {
        return new ParkingID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingID parkingID = (ParkingID) o;
        return getValue().equals(parkingID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
