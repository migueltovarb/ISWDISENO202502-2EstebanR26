package com.academix.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "inscripciones")
public class Inscripcion {
    
    @Id
    private String id;
    
    private String usuarioId;
    private String cursoId;
    private String numeroInscripcion;
    
    @CreatedDate
    private LocalDateTime fechaInscripcion;
    
    private String periodoAcademicoId;
    private String estado; // PENDIENTE, CONFIRMADA, CANCELADA
    private String estadoPago;
}
