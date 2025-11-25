package com.academix.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "recuperacion_password")
public class RecuperacionPassword {
    
    @Id
    private String id;
    
    private String usuarioId;
    private String email;
    private String token; // Código de 6 dígitos
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaExpiracion;
    private Boolean usado = false;
}