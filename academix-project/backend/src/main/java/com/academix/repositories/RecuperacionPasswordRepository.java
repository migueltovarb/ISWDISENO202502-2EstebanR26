package com.academix.repositories;

import com.academix.models.RecuperacionPassword;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RecuperacionPasswordRepository extends MongoRepository<RecuperacionPassword, String> {
    Optional<RecuperacionPassword> findByTokenAndUsadoFalse(String token);
    Optional<RecuperacionPassword> findByEmailAndUsadoFalse(String email);
}