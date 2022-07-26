package com.antoniocmoura.cloud.parking.infrastructure.parking.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository  extends JpaRepository<ParkingJpaEntity, String> {
    Page<ParkingJpaEntity> findAll(Specification<ParkingJpaEntity> whereClause, Pageable page);
}
