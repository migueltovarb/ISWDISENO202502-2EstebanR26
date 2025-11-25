package com.academix.repositories;

import com.academix.models.Profesor;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface ProfesorRepository extends MongoRepository<Profesor, String> {
    Optional<Profesor> findByEmail(String email);
    List<Profesor> findByActivoTrue();
    List<Profesor> findByDepartamento(String departamento);
}