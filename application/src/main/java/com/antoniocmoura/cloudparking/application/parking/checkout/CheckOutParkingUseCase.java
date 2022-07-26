package com.antoniocmoura.cloudparking.application.parking.checkout;

import com.antoniocmoura.cloudparking.application.parking.ParkingOutput;
import com.antoniocmoura.cloudparking.domain.exceptions.NotFoundException;
import com.antoniocmoura.cloudparking.domain.parking.Parking;
import com.antoniocmoura.cloudparking.domain.parking.ParkingGateway;
import com.antoniocmoura.cloudparking.domain.parking.ParkingID;

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
