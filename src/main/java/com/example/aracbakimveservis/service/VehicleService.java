package com.example.aracbakimveservis.service;

import com.example.aracbakimveservis.dto.VehicleCreateDTO;
import com.example.aracbakimveservis.entity.Vehicle;
import com.example.aracbakimveservis.exception.VehicleAlreadyExistsException;
import com.example.aracbakimveservis.repository.VehicleRepository;
import com.example.aracbakimveservis.exception.VehicleNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle createVehicle(VehicleCreateDTO dto){
        if (vehicleRepository.existsByPlate(dto.getPlate())) {
                throw new VehicleAlreadyExistsException("Bu plakaya sahip araç zaten kayıtlı:  " + dto.getPlate());
        }
        Vehicle vehicle = new Vehicle(dto.getPlate(),dto.getBrand(),dto.getModel(),dto.getGearType());
        return  vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public void deleteVehicle(Long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new VehicleNotFoundException("Silinecek araç bulunamadı: " + id);
        }
        vehicleRepository.deleteById(id);
    }

    public Vehicle updateVehicle(Long id, VehicleCreateDTO dto) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Güncellenecek araç bulunamadı: " + id));
        vehicle.setPlate(dto.getPlate());
        vehicle.setBrand(dto.getBrand());
        vehicle.setModel(dto.getModel());
        vehicle.setGearType(dto.getGearType());
        return vehicleRepository.save(vehicle);
    }

}
