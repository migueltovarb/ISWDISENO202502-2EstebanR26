package com.academix.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Document(collection = "Nnotificaciones")
public class Notificacion {
    @Id
    private String id;
    
    private String usuarioId;
    private String titulo;
    private String mensaje;
    private String tipo;
    private String categoria;
    
    private String referenciaTipo;
    private String referenciaId;
    
    private boolean leida;
    private boolean enviada;
    
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaLeida;
    private LocalDateTime fechaEnvio;
}