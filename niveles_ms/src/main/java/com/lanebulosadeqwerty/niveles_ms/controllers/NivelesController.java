package com.lanebulosadeqwerty.niveles_ms.controllers;

import java.util.List;

import com.lanebulosadeqwerty.niveles_ms.exceptions.NivelNoEncontradoException;
import com.lanebulosadeqwerty.niveles_ms.exceptions.NivelYaExisteException;
import com.lanebulosadeqwerty.niveles_ms.exceptions.NumeroNivelInvalidoException;
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
    List<Niveles> traerNiveles() {
        return nivelesRepositorio.findAll();
    }

    @GetMapping("/aprende/niveles/{id}")
    Niveles traerNivel(@PathVariable Integer id) {
        Niveles nivel = nivelesRepositorio.findById(id).orElse(null);
        if (nivel == null){
            throw new NivelNoEncontradoException("El nivel " + id + " no ha sido encontrado.");
        }

        return nivel;
    }

    @PostMapping("/aprende/niveles")
    Niveles nuevoNivel(@RequestBody Niveles nivel) {
        // Verificar que el numero de nivel es valido
        if (nivel.getId() < 1) {
            throw new NumeroNivelInvalidoException("El número del nivel (id) debe ser mayor o igual a 1.");   
        }
        
        // Verificar que el nivel no existe ya
        Niveles nivelIgual = nivelesRepositorio.findById(nivel.getId()).orElse(null);
        if (nivelIgual != null) {
            throw new NivelYaExisteException("No es posible crear un nivel nuevo con si un nivel con el mismo número ya existe.");
        }

        return nivelesRepositorio.save(nivel);
    }

    @PutMapping("/aprende/niveles/{idViejo}")
    Niveles actualizarNivel(@PathVariable Integer idViejo, @RequestBody Niveles nivelNuevo) {
        
        // Error: Si se está tratando de modificar un nivel inexistente
        Niveles nivelViejo = nivelesRepositorio.findById(idViejo).orElse(null);
        if (nivelViejo == null) {
            throw new NivelNoEncontradoException("No se puede modificar un nivel inexistente.");
        }
        
        // Error: si el nuevo numero de nivel es invalido
        if (nivelNuevo.getId() < 1) {
            throw new NumeroNivelInvalidoException("El número del nivel (id) debe ser mayor o igual a 1.");   
        }

        // Error: si se está tratando de asignar un número de nivel ya existente
        Niveles nivelIgual = nivelesRepositorio.findById(nivelNuevo.getId()).orElse(null);
        if (nivelIgual != null && idViejo != nivelNuevo.getId()) {
            throw new NivelYaExisteException("No es posible crear un nivel nuevo con si un nivel con el mismo número ya existe.");
        }

        // Borrar el nivel antiguo si sus ids son distintos
        if (nivelViejo.getId() != nivelNuevo.getId()) {
            nivelesRepositorio.delete(nivelViejo);
        }
        
        // Guardar nivel actualizado
        return nivelesRepositorio.save(nivelNuevo);
    }

    @DeleteMapping("/aprende/niveles/{id}")
    void borrarNivel(@PathVariable Integer id) {
        Niveles nivel = nivelesRepositorio.findById(id).orElse(null);

        // Error: Si se está tratando de modificar un nivel inexistente
        if (nivel == null) {
            throw new NivelNoEncontradoException("No se puede eliminar un nivel inexistente.");
        }
        
        // TODO Deberia borrar las lecciones de este nivel?

        nivelesRepositorio.delete(nivel);
    }
    
}
