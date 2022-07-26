package com.antoniocmoura.cloud.parking.application.parking.create;

import com.antoniocmoura.cloud.parking.application.parking.ParkingOutput;
import com.antoniocmoura.cloud.parking.domain.parking.Parking;
import com.antoniocmoura.cloud.parking.domain.parking.ParkingGateway;

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
