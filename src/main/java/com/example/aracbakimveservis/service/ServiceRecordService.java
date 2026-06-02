package com.example.aracbakimveservis.service;

import com.example.aracbakimveservis.dto.ServiceRecordCreateDTO;
import com.example.aracbakimveservis.entity.ServiceRecord;
import com.example.aracbakimveservis.entity.Vehicle;
import com.example.aracbakimveservis.exception.VehicleNotFoundException;
import com.example.aracbakimveservis.repository.ServiceRecordRepository;
import com.example.aracbakimveservis.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceRecordService {

    private final ServiceRecordRepository serviceRecordRepository;
    private final VehicleRepository vehicleRepository;

    public ServiceRecordService(ServiceRecordRepository serviceRecordRepository, VehicleRepository vehicleRepository) {
        this.serviceRecordRepository = serviceRecordRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public ServiceRecord createServiceRecord(ServiceRecordCreateDTO dto) {
        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException("Sistemde bu ID degerine sahip bir arac bulunamadi: " + dto.getVehicleId()));

        ServiceRecord serviceRecord = new ServiceRecord(dto.getDescription(), dto.getCost(), LocalDate.now(), vehicle);
        return serviceRecordRepository.save(serviceRecord);
    }

    public List<ServiceRecord> getRecordsByVehicle(Long vehicleId) {
        return serviceRecordRepository.findByVehicleId(vehicleId);
    }

    public void deleteServiceRecord(Long id) {
        if (!serviceRecordRepository.existsById(id)) {
            throw new VehicleNotFoundException("Silinecek servis kaydı bulunamadı: " + id);
        }
        serviceRecordRepository.deleteById(id);
    }

    @Transactional
    public ServiceRecord updateServiceRecord(Long id, ServiceRecordCreateDTO dto) {
        ServiceRecord record = serviceRecordRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Güncellenecek servis kaydı bulunamadı: " + id));
        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId())
                .orElseThrow(() -> new VehicleNotFoundException("Araç bulunamadı: " + dto.getVehicleId()));
        record.setDescription(dto.getDescription());
        record.setCost(dto.getCost());
        record.setVehicle(vehicle);
        return serviceRecordRepository.save(record);
    }
}