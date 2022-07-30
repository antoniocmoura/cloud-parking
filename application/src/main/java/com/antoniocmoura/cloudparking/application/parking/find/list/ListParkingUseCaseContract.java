package com.antoniocmoura.cloudparking.application.parking.find.list;

import com.antoniocmoura.cloudparking.application.UseCase;
import com.antoniocmoura.cloudparking.application.parking.ParkingOutput;
import com.antoniocmoura.cloudparking.domain.pagination.Pagination;
import com.antoniocmoura.cloudparking.domain.pagination.SearchQuery;

public abstract class ListParkingUseCaseContract
        extends UseCase<SearchQuery, Pagination<ParkingOutput>> {
}
