package com.example.aracbakimveservis.exception;

public class VehicleAlreadyExistsException extends  RuntimeException{
    public VehicleAlreadyExistsException(String message) {
        super(message);
    }
}
