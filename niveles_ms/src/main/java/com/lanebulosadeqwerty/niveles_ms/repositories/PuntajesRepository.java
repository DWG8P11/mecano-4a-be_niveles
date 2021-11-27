package com.lanebulosadeqwerty.niveles_ms.repositories;

import com.lanebulosadeqwerty.niveles_ms.models.Puntajes;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PuntajesRepository extends MongoRepository<Puntajes, String> {
    List<Puntajes> findByUsuario(String usuario);
    List<Puntajes> findByLeccionId(String leccionId);
}