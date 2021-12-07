package com.lanebulosadeqwerty.niveles_ms.repositories;

import com.lanebulosadeqwerty.niveles_ms.models.NivelLigero;
import com.lanebulosadeqwerty.niveles_ms.models.Niveles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.List;

public interface NivelesRepository extends MongoRepository<Niveles, String> {
    Optional<Niveles> findById (Integer nivel); // Optional es algo que es un Niveles o null

    @Query(fields = "{'nombre': 1, 'descripcion': 1}")
    List<NivelLigero> findAllBy();
}
