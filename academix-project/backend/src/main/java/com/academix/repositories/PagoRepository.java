package com.academix.repositories;

import com.academix.models.Pago;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface PagoRepository extends MongoRepository<Pago, String> {
    Optional<Pago> findByInscripcionId(String inscripcionId);
    List<Pago> findByEstudianteId(String estudianteId);
    List<Pago> findByEstadoPago(String estadoPago);
}