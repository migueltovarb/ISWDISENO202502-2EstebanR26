package com.academix.services;

import com.academix.models.Pago;
import com.academix.models.Inscripcion;
import com.academix.repositories.PagoRepository;
import com.academix.repositories.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PagoService {
    
    @Autowired
    private PagoRepository pagoRepository;
    
    @Autowired
    private InscripcionRepository inscripcionRepository;
    
    public List<Pago> listarTodos() {
        return pagoRepository.findAll();
    }
    
    public Pago obtenerPorId(String id) {
        return pagoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }
    
    public Pago obtenerPorInscripcion(String inscripcionId) {
        return pagoRepository.findByInscripcionId(inscripcionId).orElse(null);
    }
    
    public List<Pago> obtenerPorEstudiante(String estudianteId) {
        return pagoRepository.findByEstudianteId(estudianteId);
    }
    
    public List<Pago> obtenerPorEstado(String estadoPago) {
        return pagoRepository.findByEstadoPago(estadoPago);
    }
    
    // Método crear (alias de crearPago para el controller)
    public Pago crear(Pago pago) {
        pago.setEstadoPago("PENDIENTE");
        pago.setFechaCreacion(LocalDateTime.now());
        return pagoRepository.save(pago);
    }
    
    public Pago crearPago(Pago pago) {
        return crear(pago);
    }
    
    // NUEVO: Método actualizar
    public Pago actualizar(String id, Pago pagoActualizado) {
        Pago pago = obtenerPorId(id);
        
        // Actualizar solo los campos que pueden cambiar
        if (pagoActualizado.getMonto() != null) {
            pago.setMonto(pagoActualizado.getMonto());
        }
        if (pagoActualizado.getMetodoPago() != null) {
            pago.setMetodoPago(pagoActualizado.getMetodoPago());
        }
        if (pagoActualizado.getEstadoPago() != null) {
            pago.setEstadoPago(pagoActualizado.getEstadoPago());
        }
        if (pagoActualizado.getTransaccionId() != null) {
            pago.setTransaccionId(pagoActualizado.getTransaccionId());
        }
        
        return pagoRepository.save(pago);
    }
    
    public Pago confirmarPago(String pagoId, String transaccionId) {
        Pago pago = obtenerPorId(pagoId);
        pago.setEstadoPago("COMPLETADO");
        pago.setTransaccionId(transaccionId);
        pago.setFechaPago(LocalDateTime.now());
        
        // Actualizar estado de inscripción a ACTIVA
        Inscripcion inscripcion = inscripcionRepository.findById(pago.getInscripcionId())
            .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
        inscripcion.setEstadoPago("PAGADO");
        inscripcion.setEstado("ACTIVA");
        inscripcionRepository.save(inscripcion);
        
        return pagoRepository.save(pago);
    }
    
    public Pago marcarComoFallido(String pagoId, String motivoFallo) {
        Pago pago = obtenerPorId(pagoId);
        pago.setEstadoPago("FALLIDO");
        return pagoRepository.save(pago);
    }
    
    // NUEVO: Método eliminar
    public void eliminar(String id) {
        Pago pago = obtenerPorId(id);
        
        // Opcional: validar que el pago pueda ser eliminado
        if ("COMPLETADO".equals(pago.getEstadoPago())) {
            throw new RuntimeException("No se puede eliminar un pago completado");
        }
        
        pagoRepository.deleteById(id);
    }
}