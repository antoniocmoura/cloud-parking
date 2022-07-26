package com.antoniocmoura.cloud.parking.application.parking.delete;

import com.antoniocmoura.cloud.parking.domain.parking.ParkingGateway;
import com.antoniocmoura.cloud.parking.domain.parking.ParkingID;

import java.util.Objects;

public class DeleteParkingUseCase extends DeleteParkingUseCaseContract {

    private final ParkingGateway parkingGateway;

    public DeleteParkingUseCase(final ParkingGateway parkingGateway) {
        this.parkingGateway = Objects.requireNonNull(parkingGateway);
    }

    @Override
    public void execute(String anID) {
        this.parkingGateway.deleteById(ParkingID.from(anID));
    }
}
