package com.academix.repositories;

import com.academix.models.Horario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HorarioRepository extends MongoRepository<Horario, String> {
    List<Horario> findByCursoId(String cursoId);
    List<Horario> findByDiaSemana(String diaSemana);
}