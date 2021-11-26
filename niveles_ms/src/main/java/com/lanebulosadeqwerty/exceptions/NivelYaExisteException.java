package com.lanebulosadeqwerty.exceptions;

/*
 * Si se trata de CREAR un numero de nivel que ya existe
 */

public class NivelYaExisteException  extends RuntimeException {
    public NivelYaExisteException(String message) {
        super(message);
    }
}
