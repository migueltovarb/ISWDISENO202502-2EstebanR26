package com.academix.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "horarios")
public class Horario {
    
    @Id
    private String id;
    
    private String cursoId;
    private String diaSemana; // LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO
    private String horaInicio; // Formato: "08:00"
    private String horaFin;    // Formato: "10:00"
    private String salon;
    private String tipo; // TEORIA, PRACTICA, LABORATORIO
}