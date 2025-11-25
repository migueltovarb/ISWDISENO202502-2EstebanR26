package com.academix.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class Usuario {
    
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String email;
    
    private String password;
    private String nombre;
    private String apellido;
    private String telefono; // AGREGADO
    private String rol; // ESTUDIANTE, PROFESOR, ADMIN
    
    @Indexed(unique = true)
    private String codigoEstudiante;
    
    private String carrera;
    private Integer semestre;
    
    @CreatedDate
    private LocalDateTime fechaRegistro;
    
    private Boolean activo = true;
}