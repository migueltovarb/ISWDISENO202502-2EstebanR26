package com.example.vehicleapi.exception;

public class VehicleNotFoundException extends RuntimeException {
    
    public VehicleNotFoundException(String id) {
        super("No se encontró el vehículo con ID: " + id);
    }
    
    public VehicleNotFoundException(String campo, String valor) {
        super("No se encontró el vehículo con " + campo + ": " + valor);
    }
}
