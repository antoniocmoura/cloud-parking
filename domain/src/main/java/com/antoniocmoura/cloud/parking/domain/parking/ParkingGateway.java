package com.antoniocmoura.cloud.parking.domain.parking;

import com.antoniocmoura.cloud.parking.domain.pagination.Pagination;
import com.antoniocmoura.cloud.parking.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

public interface ParkingGateway {
    
    Parking create(Parking aParking);

    void deleteById(ParkingID anId);

    Optional<Parking> findById(ParkingID anId);

    Parking update(Parking aParking);

    Pagination<Parking> findAll(SearchQuery aQuery);
    
}
