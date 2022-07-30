package com.antoniocmoura.cloudparking.infrastructure.parking.persistence;

import com.antoniocmoura.cloudparking.domain.pagination.Pagination;
import com.antoniocmoura.cloudparking.domain.pagination.SearchQuery;
import com.antoniocmoura.cloudparking.domain.parking.Parking;
import com.antoniocmoura.cloudparking.domain.parking.ParkingGateway;
import com.antoniocmoura.cloudparking.domain.parking.ParkingID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.antoniocmoura.cloudparking.infrastructure.utils.SpecificationUtils.like;

@Service
public class ParkingPostreSQLGateway implements ParkingGateway {

    private final ParkingRepository repository;

    public ParkingPostreSQLGateway(final ParkingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Parking create(Parking aParking) {
        return this.repository.save(ParkingJpaEntity.fromAggregate(aParking)).toAggregate();
    }

    @Override
    public void deleteById(ParkingID anId) {
        final String anIdValue = anId.getValue();
        if (this.repository.existsById(anIdValue)) {
            this.repository.deleteById(anIdValue);
        }
    }

    @Override
    public Optional<Parking> findById(ParkingID anId) {
        return repository.findById(anId.getValue()).map(ParkingJpaEntity::toAggregate);
    }

    @Override
    public Parking update(Parking aParking) {
        return this.repository.save(ParkingJpaEntity.fromAggregate(aParking)).toAggregate();
    }

    @Override
    public Pagination<Parking> findAll(SearchQuery aQuery) {

        // pagination setup
        final var pagination = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        // generation of search filters
        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult = this.repository.findAll(Specification.where(specifications), pagination);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(ParkingJpaEntity::toAggregate).toList()
        );
    }

    private Specification<ParkingJpaEntity> assembleSpecification(final String str) {
        final Specification<ParkingJpaEntity> licenseLike = like("license", str);
        return licenseLike;
    }
}
