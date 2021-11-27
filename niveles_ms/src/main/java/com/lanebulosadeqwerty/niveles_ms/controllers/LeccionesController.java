package com.lanebulosadeqwerty.niveles_ms.controllers;

import java.util.List;

import com.lanebulosadeqwerty.niveles_ms.models.Lecciones;
import com.lanebulosadeqwerty.niveles_ms.repositories.LeccionesRepository;

import org.springframework.web.bind.annotation.*;

@RestController
public class LeccionesController {

    private final LeccionesRepository leccionesRepositorio;

    public LeccionesController(LeccionesRepository repositorio) {
        this.leccionesRepositorio = repositorio;
    }

    @GetMapping("/lecciones")
    List<Lecciones> getLecciones() {
        return leccionesRepositorio.findAll();
    }

    @PostMapping("/lecciones/nueva")
    Lecciones nuevaLeccion(@RequestBody Lecciones leccion) {
        return leccionesRepositorio.save(leccion);
    }
    
}
