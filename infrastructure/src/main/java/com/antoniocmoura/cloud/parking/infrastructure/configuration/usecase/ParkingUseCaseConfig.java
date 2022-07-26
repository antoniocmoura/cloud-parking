package com.antoniocmoura.cloud.parking.infrastructure.configuration.usecase;

import com.antoniocmoura.cloud.parking.application.parking.checkout.CheckOutParkingUseCase;
import com.antoniocmoura.cloud.parking.application.parking.checkout.CheckOutParkingUseCaseContract;
import com.antoniocmoura.cloud.parking.application.parking.create.CreateParkingUseCase;
import com.antoniocmoura.cloud.parking.application.parking.create.CreateParkingUseCaseContract;
import com.antoniocmoura.cloud.parking.application.parking.delete.DeleteParkingUseCase;
import com.antoniocmoura.cloud.parking.application.parking.delete.DeleteParkingUseCaseContract;
import com.antoniocmoura.cloud.parking.application.parking.find.get.GetParkingByIdUseCase;
import com.antoniocmoura.cloud.parking.application.parking.find.get.GetParkingByIdUseCaseContract;
import com.antoniocmoura.cloud.parking.application.parking.find.list.ListParkingUseCase;
import com.antoniocmoura.cloud.parking.application.parking.find.list.ListParkingUseCaseContract;
import com.antoniocmoura.cloud.parking.application.parking.update.UpdateParkingUseCase;
import com.antoniocmoura.cloud.parking.application.parking.update.UpdateParkingUseCaseContract;
import com.antoniocmoura.cloud.parking.domain.parking.ParkingGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParkingUseCaseConfig{

    private final ParkingGateway parkingGateway;

    public ParkingUseCaseConfig(final ParkingGateway parkingGateway) {
        this.parkingGateway = parkingGateway;
    }

    @Bean
    public CreateParkingUseCaseContract createParkingUseCase() {
        return new CreateParkingUseCase(parkingGateway);
    }

    @Bean
    public DeleteParkingUseCaseContract deleteParkingUseCase() {
        return new DeleteParkingUseCase(parkingGateway);
    }

    @Bean
    public UpdateParkingUseCaseContract updateParkingUseCase() {
        return new UpdateParkingUseCase(parkingGateway);
    }

    @Bean
    public GetParkingByIdUseCaseContract getParkingByIdUseCase() {
        return new GetParkingByIdUseCase(parkingGateway);
    }

    @Bean
    public ListParkingUseCaseContract listParkingUseCase() {
        return new ListParkingUseCase(parkingGateway);
    }

    @Bean
    public CheckOutParkingUseCaseContract checkOutParkingUseCase() {
        return new CheckOutParkingUseCase(parkingGateway);
    }

}
