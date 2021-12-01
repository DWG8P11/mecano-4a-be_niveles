package com.lanebulosadeqwerty.niveles_ms.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lanebulosadeqwerty.niveles_ms.models.Lecciones;
import com.lanebulosadeqwerty.niveles_ms.repositories.LeccionesRepository;
import com.lanebulosadeqwerty.niveles_ms.repositories.NivelesRepository;
import com.lanebulosadeqwerty.niveles_ms.exceptions.LeccionNoEncontradaException;
import com.lanebulosadeqwerty.niveles_ms.exceptions.LeccionYaExisteException;
import com.lanebulosadeqwerty.niveles_ms.exceptions.NivelNoEncontradoException;
import com.lanebulosadeqwerty.niveles_ms.exceptions.NumeroLeccionInvalidoException;
import com.lanebulosadeqwerty.niveles_ms.exceptions.NumeroNivelInvalidoException;
import com.lanebulosadeqwerty.niveles_ms.exceptions.PuntajesMinimosInvalidosException;
import com.lanebulosadeqwerty.niveles_ms.exceptions.TeclasInvalidasException;

import org.springframework.web.bind.annotation.*;

import java.text.Normalizer;

@RestController
public class LeccionesController {

    private final LeccionesRepository leccionesRepositorio;
    private final NivelesRepository nivelesRepositorio;

    private final List<String> arregloTeclasValidas = Arrays.asList((new String[]{"a", "s", "d", "f", "g", "h", "j", "k", "l", "ñ", "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "z", "x", "c", "v", "b", "n", "m", ",", ".", ";", ":", "¿", "?", "¡", "!", "\"", "'", "\n", " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "#", "$", "%", "&", "/", "(", ")", "=", "+", "*", "[", "]", "{", "}", "-", "_", "°", "|", "¬", "\\", "`", "~", "^", "@", "<", ">"}));

    public LeccionesController(LeccionesRepository repositorioLecciones, NivelesRepository repositorioNiveles) {
        this.leccionesRepositorio = repositorioLecciones;
        this.nivelesRepositorio = repositorioNiveles;
    }

    /*
     * CRUD
     */

    @GetMapping("/aprende/lecciones")
    List<Lecciones> traerLecciones(@RequestParam(required = false) Integer nivel) {
        /**
         * Retorna lista de lecciones, con posible filtro por nivel
         */
        // Filtrar por nivel, si se indico alguno
        if (nivel != null){
            // Error: el numero de nivel existe en la base de datos
            if (nivelesRepositorio.findById(nivel).isEmpty()){
                throw new NivelNoEncontradoException("El nivel deseado no existe.");
            }

            return leccionesRepositorio.findAllByNivel(nivel);
        }
        // Retornarlos todos, si no hubo un filtro
        return leccionesRepositorio.findAll();
    }

    @GetMapping("/aprende/lecciones/{idLeccion}")
    Lecciones traerLeccionPorId(@PathVariable String idLeccion) {
        /**
         * Retorna la leccion indicada por el id en la URL
         */
        Lecciones leccion = leccionesRepositorio.findById(idLeccion).orElse(null);

        if (leccion == null){
            throw new LeccionNoEncontradaException("No se encontró la lección deseada. Verifique el id.");
        }

        return leccion;
    }

    @GetMapping("/aprende/lecciones/{nivel}/{nLeccion}")
    Lecciones traerLeccion(@PathVariable Integer nivel, @PathVariable Integer nLeccion) {
        Lecciones leccion = traerLeccionEnNivel(nivel, nLeccion);

        if (leccion == null) {
            throw new LeccionNoEncontradaException("No se encontró la lección deseada. Verifique el nivel y el número de lección.");
        }

        return leccion;
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

        // Error: teclas de leccion invalidas
        // Primero normalizar y lower case teclas
        ArrayList<String> teclasNormalizadas = new ArrayList<>();
        String normCar;
        for (String cLeccion : leccion.getTeclas()) {
            normCar = Normalizer.normalize( cLeccion, Normalizer.Form.NFD ).toLowerCase();
            if (!teclasNormalizadas.contains(normCar)) // Evitar repeticion
                teclasNormalizadas.add(normCar);
        }
        leccion.setTeclas(teclasNormalizadas);

        for (String cLeccion : teclasNormalizadas) {
            if (!arregloTeclasValidas.contains(cLeccion)){
                throw new TeclasInvalidasException("El símbolo '" + cLeccion + "' no es parte de los símbolos permitidos para una lección.");
            }
        }

        // Error: los puntajes mínimos no son válidos
        if (leccion.getMini1() < 0 || leccion.getMini2() < leccion.getMini1() || leccion.getMini3() < leccion.getMini2() || leccion.getMini4() < leccion.getMini3()) {
            throw new PuntajesMinimosInvalidosException("Los puntajes mínimos de la lección deben ser mayores a 0 y no pueden ser decrecientes.");
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
            throw new NumeroLeccionInvalidoException("El número de nivel debe ser mayor o igual a 1.");   
        }

        // Error: el numero de nivel existe en la base de datos
        if (nivelesRepositorio.findById(leccionNueva.getNivel()).isEmpty()){
            throw new NivelNoEncontradoException("No se puede modificar una leccion para pertenecer a un nivel inexistente.");
        }

        // Error: el numero de leccion existe ya para el nivel deseado
        Lecciones leccionIgual = traerLeccionEnNivel(leccionNueva.getNivel(), leccionNueva.getN_leccion());
        if (leccionIgual != null && (nLeccionViejo != leccionNueva.getN_leccion() || nivelViejo != leccionNueva.getNivel())){
            throw new LeccionYaExisteException("El número de lección ya existe en este nivel.");
        }

        // Error: teclas de leccion invalidas
        // Primero normalizar y lower case teclas
        ArrayList<String> teclasNormalizadas = new ArrayList<>();
        String normCar;
        for (String cLeccion : leccionNueva.getTeclas()) {
            normCar = Normalizer.normalize( cLeccion, Normalizer.Form.NFD ).toLowerCase();
            if (!teclasNormalizadas.contains(normCar)) // Evitar repeticion
                teclasNormalizadas.add(normCar);
        }
        leccionNueva.setTeclas(teclasNormalizadas);

        for (String cLeccion : teclasNormalizadas) {
            if (!arregloTeclasValidas.contains(cLeccion)){
                throw new TeclasInvalidasException("El símbolo '" + cLeccion + "' no es parte de los símbolos permitidos para una lección.");
            }
        }

        // Error: los puntajes mínimos no son válidos
        if (leccionNueva.getMini1() < 0 || leccionNueva.getMini2() < leccionNueva.getMini1() || leccionNueva.getMini3() < leccionNueva.getMini2() || leccionNueva.getMini4() < leccionNueva.getMini3()) {
            throw new PuntajesMinimosInvalidosException("Los puntajes mínimos de la lección deben ser mayores a 0 y no pueden ser decrecientes.");
        }

        // Borrar la leccion antigua si su (nivel, n_leccion) son distintos
        if (nivelViejo != leccionNueva.getNivel() || nLeccionViejo != leccionNueva.getN_leccion()) {
            leccionesRepositorio.delete(leccionVieja);
        }

        // Conservar id
        leccionNueva.setId(leccionVieja.getId());
        
        // Guardar nivel actualizado
        return leccionesRepositorio.save(leccionNueva);
    }

    @PutMapping("/aprende/lecciones/{idLeccion}")
    Lecciones actualizarLeccion(@PathVariable String idLeccion, @RequestBody Lecciones leccionNueva) {
        // TODO mejorar
        Lecciones leccion = leccionesRepositorio.findById(idLeccion).orElse(null);
        if (leccion == null) {
            throw new LeccionNoEncontradaException("No se encontro la lección que desea modificar. Por favor verifique el id.");
        }

        return actualizarLeccion(leccion.getNivel(), leccion.getN_leccion(), leccionNueva);
    }

    @DeleteMapping("/aprende/lecciones/{nivelViejo}/{nLeccionViejo}")
    void borrarLeccion(@PathVariable Integer nivel, @PathVariable Integer nLeccion) {
        /**
         * Borrar la leccion que se indica en los parametros de la peticion
         * a traves de su nivel y numero de leccion
         */
        Lecciones leccion = traerLeccion(nivel, nLeccion);

        // Error: Si se está tratando de modificar un nivel inexistente
        if (leccion == null) {
            throw new LeccionNoEncontradaException("No se puede eliminar una lección inexistente.");
        }
        
        // Borrar todos los puntajes asociados a esta leccion: en API Gateway, si acaso
        
        leccionesRepositorio.delete(leccion);
    }

    @DeleteMapping("/aprende/lecciones/{idLeccion}")
    void borrarLeccion(@PathVariable String idLeccion) {
        /**
         * Borrar la leccion indicada por la variable de URL idLeccion
         */
        Lecciones leccion = leccionesRepositorio.findById(idLeccion).orElse(null);

        if (leccion == null) {
            throw new LeccionNoEncontradaException("No se encontró la lección que se desea eliminar. Verifique el id.");
        }

        leccionesRepositorio.delete(leccion);
    }

    

    /*
     * Metodos ayudantes
     */
    private Lecciones traerLeccionEnNivel(Integer nivel, Integer nLeccion) {
        List<Lecciones> leccionesEnNivel = leccionesRepositorio.findAllByNivel(nivel);
        for (Lecciones leccionEnNivel : leccionesEnNivel) {
            if (leccionEnNivel.getN_leccion() == nLeccion){
                return leccionEnNivel;
            }
        }

        return null;
    }
    
}
