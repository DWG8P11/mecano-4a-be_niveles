package com.lanebulosadeqwerty.niveles_ms.exceptions;

/*
 * Al agregar o modificar una LECCION O PUNTAJE, poniendo un número de nivel que no esté registrado
 */
public class NivelNoEncontradoException extends RuntimeException {
    public NivelNoEncontradoException(String message) {
        super(message);
    }
}
