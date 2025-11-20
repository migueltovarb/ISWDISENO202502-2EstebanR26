package com.example.vehicleapi.controller;

import com.example.vehicleapi.model.Vehicle;
import com.example.vehicleapi.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VehicleController {
    
    private final VehicleService vehicleService;
    
    // CREATE - Crear un nuevo vehículo
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicle) {
        Vehicle savedVehicle = vehicleService.createVehicle(vehicle);
        return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
    }
    
    // READ - Obtener todos los vehículos
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }
    
    // READ - Obtener un vehículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable String id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }
    
    // READ - Obtener vehículos por marca
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Vehicle>> getVehiclesByMarca(@PathVariable String marca) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByMarca(marca);
        return ResponseEntity.ok(vehicles);
    }
    
    // READ - Obtener vehículos por marca y modelo
    @GetMapping("/marca/{marca}/modelo/{modelo}")
    public ResponseEntity<List<Vehicle>> getVehiclesByMarcaAndModelo(
            @PathVariable String marca,
            @PathVariable String modelo) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByMarcaAndModelo(marca, modelo);
        return ResponseEntity.ok(vehicles);
    }
    
    // READ - Obtener vehículos por año
    @GetMapping("/anio/{anio}")
    public ResponseEntity<List<Vehicle>> getVehiclesByAnio(@PathVariable Integer anio) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByAnio(anio);
        return ResponseEntity.ok(vehicles);
    }
    
    // READ - Obtener vehículo por placa
    @GetMapping("/placa/{placa}")
    public ResponseEntity<Vehicle> getVehicleByPlaca(@PathVariable String placa) {
        Vehicle vehicle = vehicleService.getVehicleByPlaca(placa);
        return ResponseEntity.ok(vehicle);
    }
    
    // READ - Obtener vehículos disponibles
    @GetMapping("/disponibles")
    public ResponseEntity<List<Vehicle>> getAvailableVehicles() {
        List<Vehicle> vehicles = vehicleService.getAvailableVehicles();
        return ResponseEntity.ok(vehicles);
    }
    
    // READ - Obtener vehículos por tipo
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Vehicle>> getVehiclesByTipo(@PathVariable String tipo) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByTipo(tipo);
        return ResponseEntity.ok(vehicles);
    }
    
    // READ - Obtener vehículos por rango de precio
    @GetMapping("/precio")
    public ResponseEntity<List<Vehicle>> getVehiclesByPriceRange(
            @RequestParam Double min,
            @RequestParam Double max) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByPriceRange(min, max);
        return ResponseEntity.ok(vehicles);
    }
    
    // UPDATE - Actualizar un vehículo completo
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(
            @PathVariable String id,
            @Valid @RequestBody Vehicle vehicleDetails) {
        Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicleDetails);
        return ResponseEntity.ok(updatedVehicle);
    }
    
    // UPDATE - Actualizar disponibilidad de un vehículo
    @PatchMapping("/{id}/disponibilidad")
    public ResponseEntity<Vehicle> updateAvailability(
            @PathVariable String id,
            @RequestParam Boolean disponible) {
        Vehicle updatedVehicle = vehicleService.updateAvailability(id, disponible);
        return ResponseEntity.ok(updatedVehicle);
    }
    
    // DELETE - Eliminar un vehículo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteVehicle(@PathVariable String id) {
        vehicleService.deleteVehicle(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Vehículo eliminado exitosamente");
        response.put("id", id);
        return ResponseEntity.ok(response);
    }
    
    // DELETE - Eliminar todos los vehículos
    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteAllVehicles() {
        vehicleService.deleteAllVehicles();
        Map<String, String> response = new HashMap<>();
        response.put("message", "Todos los vehículos han sido eliminados");
        return ResponseEntity.ok(response);
    }
}
