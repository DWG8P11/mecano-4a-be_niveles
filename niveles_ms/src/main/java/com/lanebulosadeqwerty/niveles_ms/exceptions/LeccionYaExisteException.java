package com.lanebulosadeqwerty.niveles_ms.exceptions;

/*
 * Si se trata de CREAR un numero de nivel que ya existe
 */

public class LeccionYaExisteException  extends RuntimeException {
    public LeccionYaExisteException(String message) {
        super(message);
    }
}
