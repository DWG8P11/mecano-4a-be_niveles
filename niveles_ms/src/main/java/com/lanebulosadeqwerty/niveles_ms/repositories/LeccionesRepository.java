package com.lanebulosadeqwerty.niveles_ms.repositories;

import com.lanebulosadeqwerty.niveles_ms.models.LeccionesLigera;
import com.lanebulosadeqwerty.niveles_ms.models.Lecciones;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface LeccionesRepository extends MongoRepository<Lecciones, String> {
    List<Lecciones> findAllByNivel (Integer nivel);

    @Query(fields = "{'nivel': 1, 'n_leccion': 1, 'titulo': 1, 'texto': 1, 'teclas': 1, 'mini1': 1, 'mini2': 1, 'mini3': 1, 'mini4': 1, 'ignorarMayus': 1, 'ignorarTildes': 1, 'ignorarDieres': 1}")
    List<LeccionesLigera> findAllBy ();

    @Query(value = "{'nivel': ?0}", fields = "{'nivel': 1, 'n_leccion': 1, 'titulo': 1, 'texto': 1, 'teclas': 1, 'mini1': 1, 'mini2': 1, 'mini3': 1, 'mini4': 1, 'ignorarMayus': 1, 'ignorarTildes': 1, 'ignorarDieres': 1}")
    List<LeccionesLigera> findAllByNivelOrNivel(Integer nivel);
}
