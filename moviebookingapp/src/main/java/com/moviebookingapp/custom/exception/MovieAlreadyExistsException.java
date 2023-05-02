package com.moviebookingapp.custom.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class MovieAlreadyExistsException extends Exception {

    private static final long serialVersionUID = 1L;

    public MovieAlreadyExistsException() {

    }

    public MovieAlreadyExistsException(String message) {
        super(message);
    }
}
