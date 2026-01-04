package com.jguarnizo.bibliotecarest.exceptions;

public class BookExistException extends RuntimeException{
    public BookExistException(String message) {
        super(message);
    }

    public BookExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
