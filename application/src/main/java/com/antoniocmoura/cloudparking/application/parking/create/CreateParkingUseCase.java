package com.antoniocmoura.cloudparking.application.parking.create;

import com.antoniocmoura.cloudparking.application.parking.ParkingOutput;
import com.antoniocmoura.cloudparking.domain.parking.Parking;
import com.antoniocmoura.cloudparking.domain.parking.ParkingGateway;

import java.util.Objects;

public class CreateParkingUseCase extends CreateParkingUseCaseContract {

    private final ParkingGateway parkingGateway;

    public CreateParkingUseCase(final ParkingGateway parkingGateway) {
        this.parkingGateway = Objects.requireNonNull(parkingGateway);
    }

    @Override
    public ParkingOutput execute(final CreateParkingCommand aCommand) {
        final var aParking = Parking.newParking(
                aCommand.license(),
                aCommand.state(),
                aCommand.model(),
                aCommand.color()
        );
        aParking.validate();
        final var result = this.parkingGateway.create(aParking);
        return ParkingOutput.fromDomain(result);
    }

}
