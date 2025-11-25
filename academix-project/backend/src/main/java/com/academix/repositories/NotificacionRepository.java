package com.academix.repositories;

import com.academix.models.Notificacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface NotificacionRepository extends MongoRepository<Notificacion, String> {
    List<Notificacion> findByUsuarioId(String usuarioId);
    List<Notificacion> findByUsuarioIdAndLeidaFalse(String usuarioId);
    List<Notificacion> findByUsuarioIdOrderByFechaCreacionDesc(String usuarioId);
    long countByUsuarioIdAndLeidaFalse(String usuarioId);
}