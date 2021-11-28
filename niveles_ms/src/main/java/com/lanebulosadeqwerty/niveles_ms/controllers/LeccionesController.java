package com.lanebulosadeqwerty.niveles_ms.controllers;

import java.util.List;

import com.lanebulosadeqwerty.niveles_ms.models.Lecciones;
import com.lanebulosadeqwerty.niveles_ms.repositories.LeccionesRepository;
import com.lanebulosadeqwerty.niveles_ms.repositories.NivelesRepository;
import com.lanebulosadeqwerty.niveles_ms.exceptions.LeccionNoEncontradaException;
import com.lanebulosadeqwerty.niveles_ms.exceptions.LeccionYaExisteException;
import com.lanebulosadeqwerty.niveles_ms.exceptions.NivelNoEncontradoException;
import com.lanebulosadeqwerty.niveles_ms.exceptions.NumeroLeccionInvalidoException;
import com.lanebulosadeqwerty.niveles_ms.exceptions.NumeroNivelInvalidoException;

import org.springframework.web.bind.annotation.*;

@RestController
public class LeccionesController {

    private final LeccionesRepository leccionesRepositorio;
    private final NivelesRepository nivelesRepositorio;

    public LeccionesController(LeccionesRepository repositorioLecciones, NivelesRepository repositorioNiveles) {
        this.leccionesRepositorio = repositorioLecciones;
        this.nivelesRepositorio = repositorioNiveles;
    }

    @GetMapping("/aprende/lecciones")
    List<Lecciones> traerLecciones() {
        return leccionesRepositorio.findAll();
    }

    @PostMapping("/aprende/lecciones")
    Lecciones nuevaLeccion(@RequestBody Lecciones leccion) {
        // Verificar que el numero de leccion es valido
        if (leccion.getN_leccion() < 1) {
            throw new NumeroLeccionInvalidoException("El número del leccion debe ser mayor o igual a 1.");   
        }

        // Verificar que el numero de nivel es valido
        if (leccion.getNivel() < 1) {
            throw new NumeroNivelInvalidoException("El número de nivel debe ser mayor o igual a 1.");   
        }

        // Verificar que el numero de nivel existe en la base de datos
        if (nivelesRepositorio.findById(leccion.getNivel()).isEmpty()){
            throw new NivelNoEncontradoException("No se puede agregar una leccion a un nivel inexistente.");
        }

        // Verificar que el numero de leccion no existe ya para el nivel deseado
        List<Lecciones> leccionesEnNivel = leccionesRepositorio.findAllByNivel(leccion.getNivel());
        for (Lecciones leccionEnNivel : leccionesEnNivel) {
            if (leccionEnNivel.getN_leccion() == leccion.getN_leccion()){
                throw new LeccionYaExisteException("El número de lección ya existe en este nivel.");
            }
        }

        return leccionesRepositorio.save(leccion);
    }

    @PutMapping("/aprende/lecciones/{idViejo}")
    Lecciones actualizarLeccion(@PathVariable String idViejo, @RequestBody Lecciones nivelNuevo) {
        
        // Error: Si se está tratando de modificar un nivel inexistente
        Lecciones nivelViejo = leccionesRepositorio.findById(idViejo).orElse(null);
        if (nivelViejo == null) {
            throw new LeccionNoEncontradaException("No se puede modificar un nivel inexistente.");
        }
        
        // Error: si el nuevo numero de nivel es invalido
        if (nivelNuevo.getN_leccion() < 1) {
            throw new NumeroLeccionInvalidoException("El número del nivel (id) debe ser mayor o igual a 1.");   
        }

        // Error: si se está tratando de asignar un número de nivel ya existente
        Lecciones nivelIgual = leccionesRepositorio.findById(nivelNuevo.getId()).orElse(null);
        if (nivelIgual != null && idViejo != nivelNuevo.getId()) {
            throw new LeccionYaExisteException("No es posible crear un nivel nuevo con si un nivel con el mismo número ya existe.");
        }

        // Borrar el nivel antiguo si sus ids son distintos
        if (nivelViejo.getId() != nivelNuevo.getId()) {
            leccionesRepositorio.delete(nivelViejo);
        }
        
        // Guardar nivel actualizado
        return leccionesRepositorio.save(nivelNuevo);
    }

    @DeleteMapping("/aprende/lecciones/{id}")
    void borrarLeccion(@PathVariable String id) {
        Lecciones nivel = leccionesRepositorio.findById(id).orElse(null);

        // Error: Si se está tratando de modificar un nivel inexistente
        if (nivel == null) {
            throw new LeccionNoEncontradaException("No se puede eliminar un nivel inexistente.");
        }
        
        leccionesRepositorio.delete(nivel);
    }
    
}
