package com.mh.ga.administrative.services.exceptions;

public class DataIntegrityException extends RuntimeException {

    public DataIntegrityException() {
        super("Data integrity violated in the operation");
    }

    public DataIntegrityException(String message) {
        super(message);
    }

    public DataIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }

}
