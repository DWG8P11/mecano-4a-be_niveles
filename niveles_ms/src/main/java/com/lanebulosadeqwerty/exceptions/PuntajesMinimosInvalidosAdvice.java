package com.lanebulosadeqwerty.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@ResponseBody
public class PuntajesMinimosInvalidosAdvice {
    @ResponseBody
    @ExceptionHandler(PuntajesMinimosInvalidosException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String PuntajesMinimosInvalidosAdvice(PuntajesMinimosInvalidosException ex){
        return ex.getMessage();
    }
}