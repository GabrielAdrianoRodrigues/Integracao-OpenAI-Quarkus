package br.com.gabrieladriano.domain.exceptions;

public class InvalidTokenException extends Exception {
    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
