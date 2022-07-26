package com.antoniocmoura.cloud.parking.domain.parking;

import com.antoniocmoura.cloud.parking.domain.AggregateRoot;

import java.time.LocalDateTime;

public class Parking extends AggregateRoot<ParkingID> {

    private String license;
    private String state;
    private String model;
    private String color;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private Double bill;

    private Parking(
            final ParkingID parkingID,
            final String license,
            final String state,
            final String model,
            final String color,
            final LocalDateTime entryDate,
            final LocalDateTime exitDate,
            final Double bill
    ) {
        super(parkingID);
        this.license = license;
        this.state = state;
        this.model = model;
        this.color = color;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.bill = bill;
    }

    public static Parking newParking(
            final String aLicense,
            final String aState,
            final String aModel,
            final String aColor
    ) {
        final var id = ParkingID.unique();
        final var now = LocalDateTime.now();
        return new Parking(id, aLicense, aState, aModel, aColor, now, null, 0d);
    }

    public static Parking with(
            final String id,
            final String license,
            final String state,
            final String model,
            final String color,
            final LocalDateTime entryDate,
            final LocalDateTime exitDate,
            final Double bill
    ) {
        return new Parking(
                ParkingID.from(id),
                license,
                state,
                model,
                color,
                entryDate,
                exitDate,
                bill
        );
    }

    @Override
    public void validate() {
        ParkingValidation.validate(this);
    }

    public void checkOut() {
        validate();
        this.exitDate = LocalDateTime.now();
        final var calculatedBill = ParkingCheckOut.getBill(this);
        this.bill = calculatedBill;
    }

    public String getLicense() {
        return license;
    }

    public String getState() {
        return state;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public LocalDateTime getExitDate() {
        return exitDate;
    }

    public Double getBill() {
        return bill;
    }

    public void update(
            final String license,
            final String state,
            final String model,
            final String color) {
        this.license = license;
        this.state = state;
        this.model = model;
        this.color = color;
    }
}
