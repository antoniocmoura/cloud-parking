package com.antoniocmoura.cloudparking.infrastructure.parking.presenter;

import com.antoniocmoura.cloudparking.application.parking.ParkingOutput;
import com.antoniocmoura.cloudparking.infrastructure.parking.model.ParkingResponse;

public interface ParkingPresenter {
    public static ParkingResponse present(final ParkingOutput output) {
        return new ParkingResponse(
                output.id(),
                output.license(),
                output.state(),
                output.model(),
                output.color(),
                output.entryDate(),
                output.exitDate(),
                output.bill()
        );
    }
}
