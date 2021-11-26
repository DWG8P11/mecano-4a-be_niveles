package com.lanebulosadeqwerty.niveles_ms.controllers;

import java.util.List;

import com.lanebulosadeqwerty.niveles_ms.models.Puntajes;
import com.lanebulosadeqwerty.niveles_ms.repositories.PuntajesRepository;

import org.springframework.web.bind.annotation.*;

@RestController
public class PuntajesController {

    private final PuntajesRepository PuntajesRepositorio;

    public PuntajesController(PuntajesRepository repositorio) {
        this.PuntajesRepositorio = repositorio;
    }

    @GetMapping("/puntajes")
    List<Puntajes> getPuntajes() {
        return PuntajesRepositorio.findAll();
    }

    @PostMapping("/puntajes/nuevo")
    Puntajes nuevoNivel(@RequestBody Puntajes nivel) {
        return PuntajesRepositorio.save(nivel);
    }
    
}
