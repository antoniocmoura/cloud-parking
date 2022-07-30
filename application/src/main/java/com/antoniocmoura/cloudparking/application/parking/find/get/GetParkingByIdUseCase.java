package com.antoniocmoura.cloudparking.application.parking.find.get;

import com.antoniocmoura.cloudparking.application.parking.ParkingOutput;
import com.antoniocmoura.cloudparking.domain.exceptions.NotFoundException;
import com.antoniocmoura.cloudparking.domain.parking.Parking;
import com.antoniocmoura.cloudparking.domain.parking.ParkingGateway;
import com.antoniocmoura.cloudparking.domain.parking.ParkingID;

import java.util.Objects;

public class GetParkingByIdUseCase extends GetParkingByIdUseCaseContract {

    private final ParkingGateway parkingGateway;

    public GetParkingByIdUseCase(final ParkingGateway parkingGateway) {
        this.parkingGateway = Objects.requireNonNull(parkingGateway);
    }

    @Override
    public ParkingOutput execute(String anID) {
        final var aParkingID = ParkingID.from(anID);
        return this.parkingGateway.findById(aParkingID)
                .map(ParkingOutput::fromDomain)
                .orElseThrow(() ->  NotFoundException.with(Parking.class, aParkingID));
    }
}
