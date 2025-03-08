package com.pulsar.exception;

public class NoAvailableCopiesException extends RuntimeException {

    public NoAvailableCopiesException(String message) {
        super(message);
    }

    public NoAvailableCopiesException(String message, Throwable cause) {
        super(message, cause);
    }
}
