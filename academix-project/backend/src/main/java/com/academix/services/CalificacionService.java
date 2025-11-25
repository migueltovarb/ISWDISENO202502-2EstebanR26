package com.academix.services;

import com.academix.models.Calificacion;
import com.academix.repositories.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CalificacionService {
    
    @Autowired
    private CalificacionRepository calificacionRepository;
    
    @Autowired
    
    public List<Calificacion> listarTodas() {
        return calificacionRepository.findAll();
    }
    
    public Calificacion obtenerPorId(String id) {
        return calificacionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Calificación no encontrada"));
    }
    
    public Calificacion obtenerPorInscripcion(String inscripcionId) {
        return calificacionRepository.findByInscripcionId(inscripcionId).orElse(null);
    }
    
    public List<Calificacion> obtenerPorEstudiante(String estudianteId) {
        return calificacionRepository.findByEstudianteId(estudianteId);
    }
    
    public List<Calificacion> obtenerPorCurso(String cursoId) {
        return calificacionRepository.findByCursoId(cursoId);
    }
    
    public List<Calificacion> obtenerPorProfesor(String profesorId) {
        return calificacionRepository.findByProfesorId(profesorId);
    }
    
    public Calificacion crear(Calificacion calificacion) {
        calificacion.setEstado("EN_CURSO");
        calificacion.setFechaRegistro(LocalDateTime.now());
        calificacion.setFechaActualizacion(LocalDateTime.now());
        return calificacionRepository.save(calificacion);
    }
    
    public Calificacion actualizarNotas(String id, Calificacion calificacionActualizada) {
        Calificacion calificacion = obtenerPorId(id);
        
        if (calificacionActualizada.getNotaParcial1() != null) {
            calificacion.setNotaParcial1(calificacionActualizada.getNotaParcial1());
        }
        if (calificacionActualizada.getNotaParcial2() != null) {
            calificacion.setNotaParcial2(calificacionActualizada.getNotaParcial2());
        }
        if (calificacionActualizada.getNotaParcial3() != null) {
            calificacion.setNotaParcial3(calificacionActualizada.getNotaParcial3());
        }
        if (calificacionActualizada.getNotaProyecto() != null) {
            calificacion.setNotaProyecto(calificacionActualizada.getNotaProyecto());
        }
        if (calificacionActualizada.getNotaParticipacion() != null) {
            calificacion.setNotaParticipacion(calificacionActualizada.getNotaParticipacion());
        }
        
        calificacion.calcularNotaFinal();
        calificacion.setFechaActualizacion(LocalDateTime.now());
        
        return calificacionRepository.save(calificacion);
    }
    
    public void eliminar(String id) {
        calificacionRepository.deleteById(id);
    }
}