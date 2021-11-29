package com.lanebulosadeqwerty.niveles_ms.repositories;

import com.lanebulosadeqwerty.niveles_ms.models.Niveles;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NivelesRepository extends MongoRepository<Niveles, String> {
    Optional<Niveles> findById (Integer nivel); // Optional es algo que es un Niveles o null
}