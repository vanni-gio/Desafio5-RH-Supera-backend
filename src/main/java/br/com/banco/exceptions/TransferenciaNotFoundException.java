package br.com.banco.exceptions;

import java.util.NoSuchElementException;

public class TransferenciaNotFoundException extends NoSuchElementException {
    public TransferenciaNotFoundException() {
        super();
    }

    public TransferenciaNotFoundException(String message) {
        super(message);
    }

    public TransferenciaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
