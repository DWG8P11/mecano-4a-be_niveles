package com.lanebulosadeqwerty.niveles_ms.controllers;

import java.util.List;

import com.lanebulosadeqwerty.niveles_ms.exceptions.NivelYaExisteException;
import com.lanebulosadeqwerty.niveles_ms.models.Niveles;
import com.lanebulosadeqwerty.niveles_ms.repositories.NivelesRepository;

import org.springframework.web.bind.annotation.*;

@RestController
public class NivelesController {

    private final NivelesRepository nivelesRepositorio;

    public NivelesController(NivelesRepository repositorio) {
        this.nivelesRepositorio = repositorio;
    }

    @GetMapping("/aprende/niveles")
    List<Niveles> getNiveles() {
        return nivelesRepositorio.findAll();
    }

    @PostMapping("/aprende/niveles")
    Niveles nuevoNivel(@RequestBody Niveles nivel) {
        Niveles nivelIgual = nivelesRepositorio.findById(nivel.getId()).orElse(null);
        if (nivelIgual != null) {
            throw new NivelYaExisteException("No es posible crear un nivel nuevo con si un nivel con el mismo número ya existe.");
        }

        return nivelesRepositorio.save(nivel);
    }
    
}
