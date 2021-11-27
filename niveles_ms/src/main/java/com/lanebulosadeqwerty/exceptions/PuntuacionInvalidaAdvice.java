package com.lanebulosadeqwerty.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@ResponseBody
public class PuntuacionInvalidaAdvice {
    @ResponseBody
    @ExceptionHandler(PuntuacionInvalidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String PuntuacionInvalidaAdvice(PuntuacionInvalidaException ex){
        return ex.getMessage();
    }
}
