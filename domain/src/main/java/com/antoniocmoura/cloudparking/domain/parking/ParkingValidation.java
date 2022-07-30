package com.antoniocmoura.cloudparking.domain.parking;

import com.antoniocmoura.cloudparking.domain.exceptions.NotBlankException;
import com.antoniocmoura.cloudparking.domain.exceptions.NotNullException;

public interface ParkingValidation {

    public static void validate(Parking parking) {

        if (parking.getLicense() == null) {
            throw new NotNullException("licence");
        }
        if (parking.getLicense().isEmpty()) {
            throw new NotBlankException("licence");
        }

        if (parking.getState() == null) {
            throw new NotNullException("state");
        }
        if (parking.getState().isEmpty()) {
            throw new NotBlankException("state");
        }

        if (parking.getModel() == null) {
            throw new NotNullException("model");
        }
        if (parking.getModel().isEmpty()) {
            throw new NotBlankException("model");
        }

        if (parking.getColor() == null) {
            throw new NotNullException("color");
        }
        if (parking.getColor().isEmpty()) {
            throw new NotBlankException("color");
        }

    }

}
