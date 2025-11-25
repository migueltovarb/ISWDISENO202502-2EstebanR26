package com.academix.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Document(collection = "Ccalificaciones")
public class Calificacion {
    @Id
    private String id;
    
    private String inscripcionId;
    private String estudianteId;
    private String estudianteNombre;
    
    private String cursoId;
    private String cursoCodigo;
    private String cursoNombre;
    
    private String profesorId;
    private String profesorNombre;
    
    private Double notaParcial1;
    private Double notaParcial2;
    private Double notaParcial3;
    private Double notaProyecto;
    private Double notaParticipacion;
    
    private Double notaFinal;
    
    private Double porcentajeParcial1 = 20.0;
    private Double porcentajeParcial2 = 20.0;
    private Double porcentajeParcial3 = 20.0;
    private Double porcentajeProyecto = 30.0;
    private Double porcentajeParticipacion = 10.0;
    
    private String estado;
    private String observaciones;
    private String retroalimentacion;
    
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;
    
    public void calcularNotaFinal() {
        if (notaParcial1 != null && notaParcial2 != null && notaParcial3 != null && 
            notaProyecto != null && notaParticipacion != null) {
            this.notaFinal = (notaParcial1 * porcentajeParcial1 / 100) +
                            (notaParcial2 * porcentajeParcial2 / 100) +
                            (notaParcial3 * porcentajeParcial3 / 100) +
                            (notaProyecto * porcentajeProyecto / 100) +
                            (notaParticipacion * porcentajeParticipacion / 100);
            
            if (this.notaFinal >= 3.0) {
                this.estado = "APROBADO";
            } else {
                this.estado = "REPROBADO";
            }
        }
    }
}