package com.academix.repositories;

import com.academix.models.Calificacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface CalificacionRepository extends MongoRepository<Calificacion, String> {
    Optional<Calificacion> findByInscripcionId(String inscripcionId);
    List<Calificacion> findByEstudianteId(String estudianteId);
    List<Calificacion> findByCursoId(String cursoId);
    List<Calificacion> findByProfesorId(String profesorId);
    List<Calificacion> findByEstado(String estado);
}