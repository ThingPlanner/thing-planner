package com.thingplanner.backend.mevents.bootstrap.exception;

public class MalformedRequestException extends RuntimeException {
    public MalformedRequestException(String message) {
        super(message);
    }
}
