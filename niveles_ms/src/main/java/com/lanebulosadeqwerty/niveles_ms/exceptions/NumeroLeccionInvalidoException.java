package com.lanebulosadeqwerty.niveles_ms.exceptions;

/*
 * Al agregar o modificar una lección, usando un numero de leccion menor a 1, 
 * o al CREAR un numero de leccion para tener un numero de leccion ya existente en ese nivel
 */

public class NumeroLeccionInvalidoException  extends RuntimeException {
    public NumeroLeccionInvalidoException(String message) {
        super(message);
    }
}
