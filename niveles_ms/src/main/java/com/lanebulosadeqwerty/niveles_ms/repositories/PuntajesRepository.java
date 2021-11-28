package com.lanebulosadeqwerty.niveles_ms.repositories;

import com.lanebulosadeqwerty.niveles_ms.models.Puntajes;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PuntajesRepository extends MongoRepository<Puntajes, String> {
    List<Puntajes> findAllByUsuario(String usuario);
    List<Puntajes> findAllByLeccionId(String leccionId);
}