package com.tkc.interstellar_route_planner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GateExceptionHandler {

    @ExceptionHandler(value = {GateNotFoundException.class})
    public ResponseEntity<Object> handleGateNotFoundException
            (GateNotFoundException cloudVendorNotFoundException)
    {
        GateException cloudVendorException = new GateException(
                cloudVendorNotFoundException.getMessage(),
                cloudVendorNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(cloudVendorException, HttpStatus.NOT_FOUND);
    }

}