package com.academix.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "aulas")
public class Aula {
    
    @Id
    private String id;
    
    private String codigo;
    private String nombre;
    private String edificio;
    private Integer capacidad;
    private String tipo;
    private Integer piso;
    private Boolean activa = true;
}
