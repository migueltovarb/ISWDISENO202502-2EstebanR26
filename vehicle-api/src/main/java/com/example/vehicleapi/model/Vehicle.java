package com.example.vehicleapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    
    @Id
    private String id;
    
    @NotBlank(message = "La marca es obligatoria")
    private String marca;
    
    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;
    
    @NotNull(message = "El año es obligatorio")
    @Min(value = 1900, message = "El año debe ser mayor a 1900")
    private Integer anio;
    
    @NotBlank(message = "El color es obligatorio")
    private String color;
    
    @NotBlank(message = "La placa es obligatoria")
    private String placa;
    
    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    private Double precio;
    
    private String tipo; // SUV, Sedan, Pickup, etc.
    
    private Boolean disponible = true;
}
