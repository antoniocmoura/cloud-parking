package com.antoniocmoura.cloud.parking.application.parking.update;

import com.antoniocmoura.cloud.parking.application.parking.ParkingOutput;
import com.antoniocmoura.cloud.parking.domain.exceptions.NotFoundException;
import com.antoniocmoura.cloud.parking.domain.parking.Parking;
import com.antoniocmoura.cloud.parking.domain.parking.ParkingGateway;
import com.antoniocmoura.cloud.parking.domain.parking.ParkingID;

import java.util.Objects;

public class UpdateParkingUseCase extends UpdateParkingUseCaseContract {

    private final ParkingGateway parkingGateway;

    public UpdateParkingUseCase(final ParkingGateway parkingGateway) {
        this.parkingGateway = Objects.requireNonNull(parkingGateway);
    }

    @Override
    public ParkingOutput execute(UpdateParkingCommand aCommand) {
        final var aParkingID = ParkingID.from(aCommand.id());
        final var aParking = this.parkingGateway.findById(aParkingID)
                .orElseThrow(() ->  NotFoundException.with(Parking.class, aParkingID));
        aParking.update(
                aCommand.license(),
                aCommand.state(),
                aCommand.model(),
                aCommand.color()
        );
        aParking.validate();
        final var result = parkingGateway.update(aParking);
        return ParkingOutput.fromDomain(result);
    }
}
