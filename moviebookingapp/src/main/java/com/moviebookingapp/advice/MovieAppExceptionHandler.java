package com.moviebookingapp.advice;

import com.moviebookingapp.custom.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MovieAppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<String> handleMovieNotFoundException(MovieNotFoundException e){
        return new ResponseEntity<String>("Movie not found, please retry", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ResponseEntity<String> handleMovieAlreadyExistsException(MovieAlreadyExistsException e){
        return new ResponseEntity<String>("You are trying to add an already existing movie", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e){
        return new ResponseEntity<String>("login ID already registered, try signing in", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UnregisteredUserException.class)
    public ResponseEntity<String> handleUnregisteredUserException(UnregisteredUserException e){
        return new ResponseEntity<String>("User not registered, try signing up with us", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPasswordException(InvalidPasswordException e){
        return new ResponseEntity<String>("Invalid Password, consider resetting it", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e){
        return new ResponseEntity<String>("User not found to reset password", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<String> handleUnauthorizeException(HttpClientErrorException.Unauthorized e){
        return new ResponseEntity<String>("Unauthorized, please retry",HttpStatus.UNAUTHORIZED);
    }
}
