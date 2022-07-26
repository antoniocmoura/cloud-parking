package com.antoniocmoura.cloud.parking.application.parking.find.list;

import com.antoniocmoura.cloud.parking.application.UseCase;
import com.antoniocmoura.cloud.parking.application.parking.ParkingOutput;
import com.antoniocmoura.cloud.parking.domain.pagination.Pagination;
import com.antoniocmoura.cloud.parking.domain.pagination.SearchQuery;

public abstract class ListParkingUseCaseContract
        extends UseCase<SearchQuery, Pagination<ParkingOutput>> {
}
