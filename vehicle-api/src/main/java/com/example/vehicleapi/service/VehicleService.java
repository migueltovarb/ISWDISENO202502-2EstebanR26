package com.example.vehicleapi.service;

import com.example.vehicleapi.exception.VehicleNotFoundException;
import com.example.vehicleapi.model.Vehicle;
import com.example.vehicleapi.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {
    
    private final VehicleRepository vehicleRepository;
    
    // CREATE - Crear un nuevo vehículo
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
    
    // READ - Obtener todos los vehículos
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
    
    // READ - Obtener un vehículo por ID
    public Vehicle getVehicleById(String id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException(id));
    }
    
    // READ - Obtener vehículos por marca
    public List<Vehicle> getVehiclesByMarca(String marca) {
        return vehicleRepository.findByMarca(marca);
    }
    
    // READ - Obtener vehículos por marca y modelo
    public List<Vehicle> getVehiclesByMarcaAndModelo(String marca, String modelo) {
        return vehicleRepository.findByMarcaAndModelo(marca, modelo);
    }
    
    // READ - Obtener vehículos por año
    public List<Vehicle> getVehiclesByAnio(Integer anio) {
        return vehicleRepository.findByAnio(anio);
    }
    
    // READ - Obtener vehículo por placa
    public Vehicle getVehicleByPlaca(String placa) {
        return vehicleRepository.findByPlaca(placa)
                .orElseThrow(() -> new VehicleNotFoundException("placa", placa));
    }
    
    // READ - Obtener vehículos disponibles
    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findByDisponible(true);
    }
    
    // READ - Obtener vehículos por tipo
    public List<Vehicle> getVehiclesByTipo(String tipo) {
        return vehicleRepository.findByTipo(tipo);
    }
    
    // READ - Obtener vehículos por rango de precio
    public List<Vehicle> getVehiclesByPriceRange(Double minPrice, Double maxPrice) {
        return vehicleRepository.findByPrecioBetween(minPrice, maxPrice);
    }
    
    // UPDATE - Actualizar un vehículo
    public Vehicle updateVehicle(String id, Vehicle vehicleDetails) {
        Vehicle vehicle = getVehicleById(id);
        
        vehicle.setMarca(vehicleDetails.getMarca());
        vehicle.setModelo(vehicleDetails.getModelo());
        vehicle.setAnio(vehicleDetails.getAnio());
        vehicle.setColor(vehicleDetails.getColor());
        vehicle.setPlaca(vehicleDetails.getPlaca());
        vehicle.setPrecio(vehicleDetails.getPrecio());
        vehicle.setTipo(vehicleDetails.getTipo());
        vehicle.setDisponible(vehicleDetails.getDisponible());
        
        return vehicleRepository.save(vehicle);
    }
    
    // UPDATE - Actualizar disponibilidad
    public Vehicle updateAvailability(String id, Boolean disponible) {
        Vehicle vehicle = getVehicleById(id);
        vehicle.setDisponible(disponible);
        return vehicleRepository.save(vehicle);
    }
    
    // DELETE - Eliminar un vehículo
    public void deleteVehicle(String id) {
        Vehicle vehicle = getVehicleById(id);
        vehicleRepository.delete(vehicle);
    }
    
    // DELETE - Eliminar todos los vehículos
    public void deleteAllVehicles() {
        vehicleRepository.deleteAll();
    }
}
