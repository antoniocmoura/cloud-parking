package com.antoniocmoura.cloud.parking.infrastructure.parking.presenter;

import com.antoniocmoura.cloud.parking.application.parking.ParkingOutput;
import com.antoniocmoura.cloud.parking.infrastructure.parking.model.ParkingResponse;

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
