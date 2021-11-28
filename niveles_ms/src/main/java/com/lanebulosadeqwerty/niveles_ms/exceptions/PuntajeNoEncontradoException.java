package com.lanebulosadeqwerty.niveles_ms.exceptions;

/*
 * Al tratar de borrar un puntaje que no existe.
 */
public class PuntajeNoEncontradoException extends RuntimeException {
    public PuntajeNoEncontradoException(String message) {
        super(message);
    }
}
