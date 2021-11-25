package com.lanebulosadeqwerty.niveles_ms.controllers;

import java.util.List;

import com.lanebulosadeqwerty.niveles_ms.models.Niveles;
import com.lanebulosadeqwerty.niveles_ms.repositories.NivelesRepository;

import org.springframework.web.bind.annotation.*;

@RestController
public class NivelesController {

    private final NivelesRepository nivelesRepositorio;

    public NivelesController(NivelesRepository repositorio) {
        this.nivelesRepositorio = repositorio;
    }

    @GetMapping("/niveles")
    List<Niveles> getNiveles() {
        return nivelesRepositorio.findAll();
    }

    @PostMapping("/niveles/nuevo")
    Niveles nuevoNivel(@RequestBody Niveles nivel) {
        return nivelesRepositorio.save(nivel);
    }
    
}
