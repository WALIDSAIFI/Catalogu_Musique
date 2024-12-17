package com.catalogue.musique.Exception;

public class ChansonNotFoundException extends RuntimeException {

    public ChansonNotFoundException(String message) {
        super(message);
    }

    public ChansonNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
