package br.com.banco.exceptions;

import java.util.NoSuchElementException;

public class ContaNotFoundException extends NoSuchElementException {

    public ContaNotFoundException() {
        super();
    }

    public ContaNotFoundException(String message) {
        super(message);
    }

    public ContaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

