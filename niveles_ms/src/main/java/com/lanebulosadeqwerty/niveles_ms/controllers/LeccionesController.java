package com.lanebulosadeqwerty.niveles_ms.controllers;

import java.util.List;
import java.util.Optional;

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

    private final char[] arregloTeclasValidas = {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'ñ', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', ';', ':', ,'¿', '?', '¡', '!', '"', "'", '\n', ' ', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '#', '$', '%', '&', '/', '(', ')', '=', '+', '*', '[', ']', '{', '}', '-', '_', '°', '|', '¬', '\\', '`', '~', '^', '@', '<', '>'};

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
        // Error: el numero de leccion es valido
        if (leccion.getN_leccion() < 1) {
            throw new NumeroLeccionInvalidoException("El número del leccion debe ser mayor o igual a 1.");   
        }

        // Error: el numero de nivel es valido
        if (leccion.getNivel() < 1) {
            throw new NumeroNivelInvalidoException("El número de nivel debe ser mayor o igual a 1.");   
        }

        // Error: el numero de nivel existe en la base de datos
        if (nivelesRepositorio.findById(leccion.getNivel()).isEmpty()){
            throw new NivelNoEncontradoException("No se puede agregar una leccion a un nivel inexistente.");
        }

        // Error: el numero de leccion no existe ya para el nivel deseado
        if (traerLeccionEnNivel(leccion.getNivel(), leccion.getN_leccion()) != null){
            throw new LeccionYaExisteException("El número de lección ya existe en este nivel.");
        }

        return leccionesRepositorio.save(leccion);
    }

    @PutMapping("/aprende/lecciones/{nivelViejo}/{nLeccionViejo}")
    Lecciones actualizarLeccion(@PathVariable Integer nivelViejo, @PathVariable Integer nLeccionViejo, @RequestBody Lecciones leccionNueva) {
        
        // Error: Si se está tratando de modificar una leccion inexistente
        Lecciones leccionVieja = traerLeccionEnNivel(nivelViejo, nLeccionViejo);
        if (leccionVieja == null) {
            throw new LeccionNoEncontradaException("No se puede modificar una leccion inexistente.");
        }
        
        // Error: si el nuevo numero de leccion es invalido
        if (leccionNueva.getN_leccion() < 1) {
            throw new NumeroLeccionInvalidoException("El número de lección debe ser mayor o igual a 1.");   
        }

        // Error: si el numero de nivel es invalido
        if (leccionNueva.getNivel() < 1) {
            throw new NumeroLeccionInvalidoException("El número de lección debe ser mayor o igual a 1.");   
        }

        // Error: el numero de nivel existe en la base de datos
        if (nivelesRepositorio.findById(leccionNueva.getNivel()).isEmpty()){
            throw new NivelNoEncontradoException("No se puede modificar una leccion para pertenecer a un nivel inexistente.");
        }

        // Error: el numero de leccion no existe ya para el nivel deseado
        Lecciones leccionIgual = traerLeccionEnNivel(leccionNueva.getNivel(), leccionNueva.getN_leccion());
        if (leccionIgual != null && (nLeccionViejo != leccionNueva.getN_leccion() && nivelViejo != leccionNueva.getNivel())){
            throw new LeccionYaExisteException("El número de lección ya existe en este nivel.");
        }

        // Borrar la leccion antigua si su (nivel, n_leccion) son distintos
        if (nivelViejo != leccionNueva.getNivel() || nLeccionViejo != leccionNueva.getN_leccion()) {
            leccionesRepositorio.delete(leccionVieja);
        }
        
        // Guardar nivel actualizado
        return leccionesRepositorio.save(leccionNueva);
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

    // Metodos ayudantes
    private Lecciones traerLeccionEnNivel(Integer nNivel, Integer nLeccion) {
        List<Lecciones> leccionesEnNivel = leccionesRepositorio.findAllByNivel(nNivel);
        for (Lecciones leccionEnNivel : leccionesEnNivel) {
            if (leccionEnNivel.getN_leccion() == nLeccion){
                return leccionEnNivel;
            }
        }

        return null;
    }
    
}
