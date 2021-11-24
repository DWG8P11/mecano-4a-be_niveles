package com.lanebulosadeqwerty.niveles_ms.repositories;

import com.lanebulosadeqwerty.niveles_ms.models.Lecciones;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface LeccionesRepository extends MongoRepository<Lecciones, String> {
    // -- lecciones..
    List<Lecciones> findByLeccionesOrigin (String leccionesOrigin);
    List<Lecciones> findByLeccionesDestiny (String leccionesDestiny);
}
