package com.academix.repositories;

import com.academix.models.Inscripcion;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface InscripcionRepository extends MongoRepository<Inscripcion, String> {
    List<Inscripcion> findByUsuarioId(String usuarioId);
    List<Inscripcion> findByCursoId(String cursoId);
    List<Inscripcion> findByEstado(String estado);
    long countByUsuarioIdAndCursoIdAndEstadoNot(String usuarioId, String cursoId, String estado);
}