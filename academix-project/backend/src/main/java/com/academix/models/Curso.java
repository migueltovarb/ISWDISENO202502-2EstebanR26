package com.academix.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Ccursos")
public class Curso {
    
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String codigo;
    
    private String nombre;
    private String descripcion;
    private Integer creditos;
    private String nivel;
    private String carrera;
    private BigDecimal precio;
    private Integer cupoMaximo;
    private Integer cupoDisponible;
    private Boolean activo = true;
    private String profesorId;
}
