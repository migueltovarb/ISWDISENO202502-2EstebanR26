package com.example.vehicleapi.repository;

import com.example.vehicleapi.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    
    // Métodos de consulta personalizados
    List<Vehicle> findByMarca(String marca);
    
    List<Vehicle> findByMarcaAndModelo(String marca, String modelo);
    
    List<Vehicle> findByAnio(Integer anio);
    
    Optional<Vehicle> findByPlaca(String placa);
    
    List<Vehicle> findByDisponible(Boolean disponible);
    
    List<Vehicle> findByTipo(String tipo);
    
    List<Vehicle> findByPrecioBetween(Double precioMin, Double precioMax);
}
