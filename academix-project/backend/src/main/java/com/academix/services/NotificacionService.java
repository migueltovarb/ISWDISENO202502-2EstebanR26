package com.academix.services;

import com.academix.models.Notificacion;
import com.academix.repositories.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionService {
    
    @Autowired
    private NotificacionRepository notificacionRepository;
    
    public List<Notificacion> obtenerPorUsuario(String usuarioId) {
        return notificacionRepository.findByUsuarioIdOrderByFechaCreacionDesc(usuarioId);
    }
    
    public List<Notificacion> obtenerNoLeidas(String usuarioId) {
        return notificacionRepository.findByUsuarioIdAndLeidaFalse(usuarioId);
    }
    
    public long contarNoLeidas(String usuarioId) {
        return notificacionRepository.countByUsuarioIdAndLeidaFalse(usuarioId);
    }
    
    public Notificacion crearNotificacion(String usuarioId, String titulo, String mensaje,
                                         String categoria, String referenciaTipo, String referenciaId) {
        Notificacion notificacion = new Notificacion();
        notificacion.setUsuarioId(usuarioId);
        notificacion.setTitulo(titulo);
        notificacion.setMensaje(mensaje);
        notificacion.setTipo("INFO");
        notificacion.setCategoria(categoria);
        notificacion.setReferenciaTipo(referenciaTipo);
        notificacion.setReferenciaId(referenciaId);
        notificacion.setLeida(false);
        notificacion.setEnviada(false);
        notificacion.setFechaCreacion(LocalDateTime.now());
        
        return notificacionRepository.save(notificacion);
    }
    
    public Notificacion marcarComoLeida(String id) {
        Notificacion notificacion = notificacionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
        notificacion.setLeida(true);
        notificacion.setFechaLeida(LocalDateTime.now());
        return notificacionRepository.save(notificacion);
    }
    
    public void marcarTodasComoLeidas(String usuarioId) {
        List<Notificacion> notificaciones = obtenerNoLeidas(usuarioId);
        notificaciones.forEach(n -> {
            n.setLeida(true);
            n.setFechaLeida(LocalDateTime.now());
        });
        notificacionRepository.saveAll(notificaciones);
    }
    
    public void eliminar(String id) {
        notificacionRepository.deleteById(id);
    }
}