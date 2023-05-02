package com.moviebookingapp.custom.exception;

public class UnregisteredUserException extends Exception {

    private static final long serialVersionUID = 1L;

    public UnregisteredUserException() {

    }

    public UnregisteredUserException(String message) {
        super(message);
    }
}
