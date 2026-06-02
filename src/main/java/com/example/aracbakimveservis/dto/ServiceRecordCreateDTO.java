package com.example.aracbakimveservis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ServiceRecordCreateDTO {

    @NotNull
    private Long vehicleId;

    @NotBlank
    private String description;

    @NotNull
    @Positive
    private Double cost;

    public ServiceRecordCreateDTO() {
    }

    public Long getVehicleId() { return vehicleId; }
    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
}