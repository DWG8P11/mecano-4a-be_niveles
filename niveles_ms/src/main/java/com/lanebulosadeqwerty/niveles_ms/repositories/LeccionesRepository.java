package com.lanebulosadeqwerty.niveles_ms.repositories;

import com.lanebulosadeqwerty.niveles_ms.models.Lecciones;
import com.lanebulosadeqwerty.niveles_ms.models.Niveles;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface LeccionesRepository extends MongoRepository<Lecciones, String> {
    List<Lecciones> findByNivel (Integer nivel);
}
