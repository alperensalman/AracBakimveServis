package com.example.aracbakimveservis.controller;

import com.example.aracbakimveservis.dto.ServiceRecordCreateDTO;
import com.example.aracbakimveservis.entity.ServiceRecord;
import com.example.aracbakimveservis.service.ServiceRecordService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceRecordController {

    private final ServiceRecordService serviceRecordService;

    public ServiceRecordController(ServiceRecordService serviceRecordService) {
        this.serviceRecordService = serviceRecordService;
    }

    @PostMapping
    public ResponseEntity<ServiceRecord> createServiceRecord(@Valid @RequestBody ServiceRecordCreateDTO dto) {
        ServiceRecord createdRecord = serviceRecordService.createServiceRecord(dto);
        return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<ServiceRecord>> getRecordsByVehicleId(@PathVariable Long vehicleId) {
        List<ServiceRecord> records = serviceRecordService.getRecordsByVehicle(vehicleId);
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceRecord> updateServiceRecord(@PathVariable Long id, @Valid @RequestBody ServiceRecordCreateDTO dto) {
        ServiceRecord updated = serviceRecordService.updateServiceRecord(id, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceRecord(@PathVariable Long id) {
        serviceRecordService.deleteServiceRecord(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}