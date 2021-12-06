package com.lanebulosadeqwerty.niveles_ms.exceptions;

/*
 * Al agregar o modificar una LECCION O PUNTAJE, poniendo un número de nivel que no esté registrado
 */
public class LeccionNoEncontradaException extends RuntimeException {
    public LeccionNoEncontradaException(String message) {
        super(message);
    }
}
