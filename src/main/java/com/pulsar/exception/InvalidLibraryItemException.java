package com.pulsar.exception;

public class InvalidLibraryItemException extends RuntimeException {

    public InvalidLibraryItemException(String message) {
        super(message);
    }

    public InvalidLibraryItemException(String message, Throwable cause) {
        super(message, cause);
    }
}
