package com.antoniocmoura.cloudparking.application.parking.find.list;

import com.antoniocmoura.cloudparking.application.parking.ParkingOutput;
import com.antoniocmoura.cloudparking.domain.pagination.Pagination;
import com.antoniocmoura.cloudparking.domain.pagination.SearchQuery;
import com.antoniocmoura.cloudparking.domain.parking.ParkingGateway;

import java.util.Objects;

public class ListParkingUseCase extends ListParkingUseCaseContract {

    private final ParkingGateway parkingGateway;

    public ListParkingUseCase(final ParkingGateway parkingGateway) {
        this.parkingGateway = Objects.requireNonNull(parkingGateway);
    }

    @Override
    public Pagination<ParkingOutput> execute(final SearchQuery aQuery) {
        return this.parkingGateway.findAll(aQuery)
                .map(ParkingOutput::fromDomain);
    }
}
