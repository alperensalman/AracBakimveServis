package com.example.aracbakimveservis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class VehicleCreateDTO {

    @NotBlank
    @Size(min = 5, max = 15)
    private String plate;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank
    private String gearType;

    public VehicleCreateDTO() {
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate){
        this.plate = plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model){
        this.model = model;
    }

    public String getGearType() {
        return gearType;
    }
    public void setGearType(String gearType){
        this.gearType = gearType;
    }
}
