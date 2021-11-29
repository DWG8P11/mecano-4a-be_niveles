package com.lanebulosadeqwerty.niveles_ms.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lanebulosadeqwerty.niveles_ms.exceptions.FiltroParaBorradoRequeridoException;
import com.lanebulosadeqwerty.niveles_ms.exceptions.LeccionNoEncontradaException;
import com.lanebulosadeqwerty.niveles_ms.exceptions.PuntajeNoEncontradoException;
import com.lanebulosadeqwerty.niveles_ms.exceptions.PuntuacionInvalidaException;
import com.lanebulosadeqwerty.niveles_ms.models.Lecciones;
import com.lanebulosadeqwerty.niveles_ms.models.Puntajes;
import com.lanebulosadeqwerty.niveles_ms.repositories.LeccionesRepository;
import com.lanebulosadeqwerty.niveles_ms.repositories.PuntajesRepository;

import org.springframework.web.bind.annotation.*;

@RestController
public class PuntajesController {

    private final PuntajesRepository puntajesRepositorio;
    private final LeccionesRepository leccionesRepositorio;

    public PuntajesController(PuntajesRepository puntajesRepositorio, LeccionesRepository leccionesRepositorio) {
        this.puntajesRepositorio = puntajesRepositorio;
        this.leccionesRepositorio = leccionesRepositorio;
    }

    @GetMapping("/aprende/puntajes")
    List<Puntajes> traerPuntajes(@RequestParam(required = false) String usuario, @RequestParam(required = false) String idLeccion) {
        /**
         * Retorna lista de puntajes, con posible filtro por usuario y/o por id de leccion
         */
        if (usuario == null && idLeccion == null) {
            return puntajesRepositorio.findAll();
        }

        // Error: leccion no existe
        if (idLeccion != null) {
            if (leccionesRepositorio.findById(idLeccion).orElse(null) == null){
                throw new LeccionNoEncontradaException("La lección no fue encontrada.");
            }
        }

        // TODO Error: usuario no existe 

        // Procesar filtros
        if (usuario != null && idLeccion == null) {
            return puntajesRepositorio.findAllByUsuario(usuario);
        }
        if (usuario == null && idLeccion != null) {
            return puntajesRepositorio.findAllByLeccionId(idLeccion);
        }

        // Nota: basado en metodo ayudante traerPuntajesUsuarioLeccion, que devuelve excepcion si la leccion no existe
        return traerPuntajesUsuarioLeccion(usuario, idLeccion); 
    }

    @GetMapping("/aprende/puntajes/numeros")
    List<Puntajes> traerPuntajesUsuarioLeccion(@RequestParam(required = false) String usuario, @RequestParam Integer nivel, @RequestParam Integer nLeccion) {
        /**
         * Retorna lista de puntajes con filtro por leccion indicada por (nivel, nLeccion),
         * y con filtro opcional por usuario
         */

        // Nota: basado en metodo ayudante traerPuntajesUsuarioLeccion, que devuelve excepcion si la leccion no existe
        return traerPuntajesUsuarioLeccion(usuario, traerIdLeccionPorNivLec(nivel, nLeccion));
    }

    @GetMapping("/aprende/puntajes/{idPuntaje}")
    Puntajes traerPuntaje(@PathVariable String idPuntaje) {
        Puntajes puntaje = puntajesRepositorio.findById(idPuntaje).orElse(null);

        if (puntaje == null) {
            throw new PuntajeNoEncontradoException("El puntaje pedido no fue encontrado. Rectifique el id.");
        }

        return puntaje;
    }


    @PostMapping("/aprendizaje/puntajes")
    Puntajes nuevoPuntaje(@RequestBody Puntajes puntaje) {
        // Error: TODO Usuario no existe

        // Error: Leccion no existe
        if (leccionesRepositorio.findById(puntaje.getLeccionId()).orElse(null) == null) {
            throw new LeccionNoEncontradaException("Lección no encontrada. No se puede asociar un puntaje a una lección inexistente.");
        }

        // Error: Puntuacion invalida
        if (puntaje.getCpm_e() < 0 || puntaje.getPrecision() < 0 || puntaje.getPrecision() > 1 || puntaje.getSegundos() < 0) {
            throw new PuntuacionInvalidaException("Los puntajes reportados no se encuentran dentro del rango válido.");
        }

        return puntajesRepositorio.save(puntaje);
    }

    @DeleteMapping("/aprende/puntajes/{idPuntaje}")
    void borrarPuntaje(@PathVariable String idPuntaje){
        Puntajes puntaje = puntajesRepositorio.findById(idPuntaje).orElse(null);

        if (puntaje == null) {
            throw new PuntajeNoEncontradoException("El puntaje pedido no fue encontrado. Rectifique el id.");
        }

        puntajesRepositorio.delete(puntaje);
    }

    @DeleteMapping("/aprende/puntajes")
    void borrarPuntajes(@RequestParam(required = false) String usuario, @RequestParam(required = false) String idLeccion) {
        /**
         * Retorna lista de puntajes, con posible filtro por usuario y/o por id de leccion
         */

        // Error: leccion no existe
        if (idLeccion != null) {
            if (leccionesRepositorio.findById(idLeccion).orElse(null) == null){
                throw new LeccionNoEncontradaException("La lección no fue encontrada.");
            }
        }

        // TODO Error: usuario no existe

        if (usuario == null && idLeccion == null) {
            throw new FiltroParaBorradoRequeridoException("No es posible borrar todos las puntuaciones con una sola petición.");
        }
        if (usuario != null && idLeccion == null) {
            // puntajesRepositorio.deleteAllByUsuario(Arrays.asList(new String[] {usuario}));
            for (Puntajes puntajeUsuario : puntajesRepositorio.findAllByUsuario(usuario)) {
                puntajesRepositorio.delete(puntajeUsuario);
            }
        }
        if (usuario == null && idLeccion != null) {
            // puntajesRepositorio.deleteAllByLeccionId(Arrays.asList(new String[] {idLeccion}));
            for (Puntajes puntajeLeccion : puntajesRepositorio.findAllByLeccionId(idLeccion)) {
                puntajesRepositorio.delete(puntajeLeccion);
            }
        }

        // Borrar todos los puntajes del usuario indicado que están en la lección indicada
        for (Puntajes puntajeUsuario : puntajesRepositorio.findAllByUsuario(usuario)) {
            if (puntajeUsuario.getLeccionId() != null && puntajeUsuario.getLeccionId().equals(idLeccion)) {
                puntajesRepositorio.delete(puntajeUsuario);
            }
        }
    }


    /*
     * Metodos ayudantes
     */
    
    private List<Puntajes> traerPuntajesUsuarioLeccion(String usuario, String idLeccion) {
        /**
         * Devuelve la lista de puntajes del usuario en la leccion identificada por idLeccion.
         * Si idLeccion no identifica a una leccion, devuelve la excepcion adecuada.
         */
        //TODO Verificar que el usuario existe?

        // Error: la leccion no existe
        if (leccionesRepositorio.findById(idLeccion).orElse(null) == null){
            throw new LeccionNoEncontradaException("No es posible cargar los puntajes deseados ya que la lección indicada no existe.");
        }

        // Lista de puntajes solo de la leccion deseada
        List<Puntajes> listaPuntajesUsuario = puntajesRepositorio.findAllByUsuario(usuario);
        List<Puntajes> rta = new ArrayList<>();

        for (Puntajes puntajeUsuario : listaPuntajesUsuario) {
            if (puntajeUsuario.getLeccionId() != null && puntajeUsuario.getLeccionId().equals(idLeccion)){
                rta.add(puntajeUsuario);
            }
        }

        return rta;
    }

    private String traerIdLeccionPorNivLec(Integer nivel, Integer nLeccion) {
        List<Lecciones> listaLeccionesNivel = leccionesRepositorio.findAllByNivel(nivel);
        for (Lecciones leccion : listaLeccionesNivel) {
            if (leccion.getNivel() == nivel && leccion.getN_leccion() == nLeccion){
                return leccion.getId(); // Asume que solo hay uno... y eso deberia ser cierto
            }
        }

        return null;
    }
    
}
