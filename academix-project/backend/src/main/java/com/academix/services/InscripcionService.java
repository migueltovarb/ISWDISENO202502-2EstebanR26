package com.academix.services;

import com.academix.models.Inscripcion;
import com.academix.models.Curso;
import com.academix.models.Pago;
import com.academix.repositories.InscripcionRepository;
import com.academix.repositories.CursoRepository;
import com.academix.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InscripcionService {
    
    @Autowired
    private InscripcionRepository inscripcionRepository;
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private PagoRepository pagoRepository;
    
    public List<Inscripcion> listarTodas() {
        return inscripcionRepository.findAll();
    }
    
    public Inscripcion obtenerPorId(String id) {
        return inscripcionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
    }
    
    public List<Inscripcion> obtenerPorEstudiante(String estudianteId) {
        return inscripcionRepository.findByUsuarioId(estudianteId);
    }
    
    public List<Inscripcion> obtenerPorCurso(String cursoId) {
        return inscripcionRepository.findByCursoId(cursoId);
    }
    
    public List<Inscripcion> obtenerPorEstado(String estado) {
        return inscripcionRepository.findByEstado(estado);
    }
    
    public Inscripcion inscribirse(Inscripcion inscripcion) {
        // Verificar si ya está inscrito
        long count = inscripcionRepository.countByUsuarioIdAndCursoIdAndEstadoNot(
            inscripcion.getUsuarioId(), 
            inscripcion.getCursoId(), 
            "CANCELADA"
        );
        
        if (count > 0) {
            throw new RuntimeException("Ya estás inscrito en este curso");
        }
        
        // Verificar cupos disponibles
        Curso curso = cursoRepository.findById(inscripcion.getCursoId())
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        
        if (curso.getCupoDisponible() == null || curso.getCupoDisponible() <= 0) {
            throw new RuntimeException("No hay cupos disponibles");
        }
        
        // Reducir cupo
        curso.setCupoDisponible(curso.getCupoDisponible() - 1);
        cursoRepository.save(curso);
        
        // Crear inscripción
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        inscripcion.setEstado("PENDIENTE_PAGO");
        inscripcion.setEstadoPago("PENDIENTE");
        Inscripcion inscripcionGuardada = inscripcionRepository.save(inscripcion);
        
        // Crear pago pendiente automáticamente
        Pago pago = new Pago();
        pago.setInscripcionId(inscripcionGuardada.getId());
        pago.setEstudianteId(inscripcion.getUsuarioId());
        pago.setMonto(curso.getPrecio());
        pago.setEstadoPago("PENDIENTE");  // ✅ CORREGIDO
        pago.setFechaCreacion(LocalDateTime.now());
        pagoRepository.save(pago);
        
        return inscripcionGuardada;
    }
    
    public Inscripcion actualizar(String id, Inscripcion inscripcion) {
        Inscripcion existente = obtenerPorId(id);
        
        if (inscripcion.getEstado() != null) {
            existente.setEstado(inscripcion.getEstado());
        }
        if (inscripcion.getEstadoPago() != null) {
            existente.setEstadoPago(inscripcion.getEstadoPago());
        }
        
        return inscripcionRepository.save(existente);
    }
    
    public void eliminar(String id) {
        inscripcionRepository.deleteById(id);
    }
}