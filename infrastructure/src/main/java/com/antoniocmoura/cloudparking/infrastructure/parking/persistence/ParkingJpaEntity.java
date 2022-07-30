package com.antoniocmoura.cloudparking.infrastructure.parking.persistence;

import com.antoniocmoura.cloudparking.domain.parking.Parking;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity(name = "Parking")
@Table(name = "parking")
public class ParkingJpaEntity {
    @Id
    private String id;
    private String license;
    private String state;
    private String model;
    private String color;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private Double bill;

    public ParkingJpaEntity(
            final String id,
            final String license,
            final String state,
            final String model,
            final String color,
            final LocalDateTime entryDate,
            final LocalDateTime exitDate,
            final Double bill) {
        this.id = id;
        this.license = license;
        this.state = state;
        this.model = model;
        this.color = color;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.bill = bill;
    }

    public ParkingJpaEntity() {
    }

    public static ParkingJpaEntity fromAggregate(Parking aParking) {
        return new ParkingJpaEntity(
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

    public Parking toAggregate() {
        return Parking.with(
                this.id,
                this.license,
                this.state,
                this.model,
                this.color,
                this.entryDate,
                this.exitDate,
                this.bill
        );
    }

}
