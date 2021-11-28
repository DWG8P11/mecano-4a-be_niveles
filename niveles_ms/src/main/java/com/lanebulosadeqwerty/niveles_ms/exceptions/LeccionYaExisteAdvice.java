package com.lanebulosadeqwerty.niveles_ms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
@ResponseBody
public class LeccionYaExisteAdvice {
    @ResponseBody
    @ExceptionHandler(LeccionYaExisteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String LeccionYaExisteAdvice(LeccionYaExisteException ex){
        return ex.getMessage();
    }
}
