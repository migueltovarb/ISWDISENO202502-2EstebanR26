package com.academix.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "profesores")
public class Profesor {
    
    @Id
    private String id;
    
    private String nombre;
    private String apellido;
    private String email;
    private String especialidad;
    private String telefono;
    private String departamento;
    private String gradoAcademico;
    private Boolean activo = true;
    private List<String> cursosAsignados = new ArrayList<>();
    private LocalDateTime fechaContratacion;
    private LocalDateTime fechaActualizacion;
}