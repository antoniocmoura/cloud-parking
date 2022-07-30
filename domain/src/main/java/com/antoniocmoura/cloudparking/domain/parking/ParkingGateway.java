package com.antoniocmoura.cloudparking.domain.parking;

import com.antoniocmoura.cloudparking.domain.pagination.Pagination;
import com.antoniocmoura.cloudparking.domain.pagination.SearchQuery;

import java.util.Optional;

public interface ParkingGateway {
    
    Parking create(Parking aParking);

    void deleteById(ParkingID anId);

    Optional<Parking> findById(ParkingID anId);

    Parking update(Parking aParking);

    Pagination<Parking> findAll(SearchQuery aQuery);
    
}
