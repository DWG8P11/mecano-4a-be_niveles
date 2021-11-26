package com.lanebulosadeqwerty.niveles_ms.repositories;

import com.lanebulosadeqwerty.niveles_ms.models.Niveles;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NivelesRepository extends MongoRepository<Niveles, String> {
    List<Niveles> findById (Integer nivel);
}