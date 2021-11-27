package com.lanebulosadeqwerty.exceptions;

/*
 * Al agregar o modificar una lección, indicando un conjunto de teclas que no se encuentre en el conjunto de teclas validas 
 * (Este arreglo se puede crear, por ejemplo, de la siguiente manera: int[] arregloTeclasValidas = {'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'ñ', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', ';', ':', ,'¿', '?', '¡', '!', '"', "'", '\n', ' ', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '#', '$', '%', '&', '/', '(', ')', '=', '+', '*', '[', ']', '{', '}', '-', '_', '°', '|', '¬', '\\', '`', '~', '^', '@'};)
 */
public class TeclasInvalidasException  extends RuntimeException {
    public TeclasInvalidasException(String message) {
        super(message);
    }
}
