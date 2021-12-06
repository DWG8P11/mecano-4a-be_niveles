package com.lanebulosadeqwerty.niveles_ms.exceptions;

/*
 * Si se trata de crear o modificar un nivel menos a 1
 */
public class NumeroNivelInvalidoException  extends RuntimeException {
    public NumeroNivelInvalidoException(String message) {
        super(message);
    }
}
