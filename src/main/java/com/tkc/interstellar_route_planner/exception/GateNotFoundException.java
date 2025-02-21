package com.tkc.interstellar_route_planner.exception;

public class GateNotFoundException  extends RuntimeException {

    public GateNotFoundException(String message) {
        super(message);
    }

    public GateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}