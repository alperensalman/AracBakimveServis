package com.example.aracbakimveservis.repository;

import com.example.aracbakimveservis.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByBrand(String brand);

    boolean existsByPlate(String plate);

    List<Vehicle> findByBrandAndModel(String brand, String model);

    List<Vehicle> findByGearType(String gearType);

    List<Vehicle> findByBrandIgnoreCase(String brand);
}