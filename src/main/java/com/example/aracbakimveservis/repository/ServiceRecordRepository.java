package com.example.aracbakimveservis.repository;

import com.example.aracbakimveservis.entity.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {

    List<ServiceRecord> findByVehicleId(Long vehicleId);

    List<ServiceRecord> findByCostBetween(Double minCost, Double maxCost);

    List<ServiceRecord> findByServiceDateAfter(java.time.LocalDate date);

    List<ServiceRecord> findByDescriptionContainingIgnoreCase(String keyword);
}