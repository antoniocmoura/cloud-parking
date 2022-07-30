package com.antoniocmoura.cloudparking.application.parking.create;

import com.antoniocmoura.cloudparking.application.UseCase;
import com.antoniocmoura.cloudparking.application.parking.ParkingOutput;

public abstract class CreateParkingUseCaseContract
        extends UseCase<CreateParkingCommand, ParkingOutput> {
}
