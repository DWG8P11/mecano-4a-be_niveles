package com.lanebulosadeqwerty.niveles_ms.repositories;

import com.lanebulosadeqwerty.niveles_ms.models.Niveles;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NivelesRepository extends MongoRepository<Niveles, String> {
    
}