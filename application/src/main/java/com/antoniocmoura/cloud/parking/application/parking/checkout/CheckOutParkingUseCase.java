package com.antoniocmoura.cloud.parking.application.parking.checkout;

import com.antoniocmoura.cloud.parking.application.parking.ParkingOutput;
import com.antoniocmoura.cloud.parking.domain.exceptions.NotFoundException;
import com.antoniocmoura.cloud.parking.domain.parking.Parking;
import com.antoniocmoura.cloud.parking.domain.parking.ParkingGateway;
import com.antoniocmoura.cloud.parking.domain.parking.ParkingID;

public class CheckOutParkingUseCase extends CheckOutParkingUseCaseContract {

    private final ParkingGateway parkingGateway;

    public CheckOutParkingUseCase(final ParkingGateway parkingGateway) {
        this.parkingGateway = parkingGateway;
    }

    @Override
    public ParkingOutput execute(final CheckOutParkingCommand aCommand) {
        final var aParkingID = ParkingID.from(aCommand.id());
        final var aParking = this.parkingGateway.findById(aParkingID)
                .orElseThrow(() ->  NotFoundException.with(Parking.class, aParkingID));
        aParking.checkOut();
        final var result = parkingGateway.update(aParking);
        return ParkingOutput.fromDomain(result);
    }
}
