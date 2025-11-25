package com.academix.repositories;

import com.academix.models.Curso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends MongoRepository<Curso, String> {
    Optional<Curso> findByCodigo(String codigo);
    List<Curso> findByActivoTrue();
    List<Curso> findByCarrera(String carrera);
    List<Curso> findByProfesorId(String profesorId);
}
