package com.lanebulosadeqwerty.niveles_ms.controllers;

import java.util.ArrayList;
import java.util.List;

import com.lanebulosadeqwerty.niveles_ms.exceptions.LeccionNoEncontradaException;
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
    List<Puntajes> traerPuntajes(@RequestParam String usuario, @RequestParam(required = false) String idLeccion) {
        /**
         * Retorna lista de puntajes, con posible filtro por usuario y/o por id de leccion
         */
        if (usuario == null && idLeccion == null) {
            return puntajesRepositorio.findAll();
        }
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


    @PostMapping("/aprendizaje/puntajes")
    Puntajes nuevoPuntaje(@RequestBody Puntajes puntaje) {
        return puntajesRepositorio.save(puntaje);
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
