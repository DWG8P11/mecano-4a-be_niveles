package com.lanebulosadeqwerty.niveles_ms.exceptions;

/**
 * Si se trata de agregar una leccion, con un puntaje min1 < 0, 
 * un puntaje min2 < min1, uno min3 < min2, y uno min4 < min3
 */
public class PuntajesMinimosInvalidosException  extends RuntimeException {
    public PuntajesMinimosInvalidosException(String message) {
        super(message);
    }
}
