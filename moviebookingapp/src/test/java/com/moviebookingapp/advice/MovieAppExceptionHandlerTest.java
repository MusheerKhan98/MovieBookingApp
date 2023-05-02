package com.moviebookingapp.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.moviebookingapp.custom.exception.InvalidPasswordException;
import com.moviebookingapp.custom.exception.MovieAlreadyExistsException;
import com.moviebookingapp.custom.exception.MovieNotFoundException;
import com.moviebookingapp.custom.exception.UnregisteredUserException;
import com.moviebookingapp.custom.exception.UserAlreadyExistsException;
import com.moviebookingapp.custom.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class MovieAppExceptionHandlerTest {
    /**
     * Method under test: {@link MovieAppExceptionHandler#handleMovieNotFoundException(MovieNotFoundException)}
     */
    @Test
    void testHandleMovieNotFoundException() {
        MovieAppExceptionHandler movieAppExceptionHandler = new MovieAppExceptionHandler();
        ResponseEntity<String> actualHandleMovieNotFoundExceptionResult = movieAppExceptionHandler
                .handleMovieNotFoundException(new MovieNotFoundException("An error occurred"));
        assertEquals("Movie not found, please retry", actualHandleMovieNotFoundExceptionResult.getBody());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleMovieNotFoundExceptionResult.getStatusCode());
        assertTrue(actualHandleMovieNotFoundExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link MovieAppExceptionHandler#handleMovieAlreadyExistsException(MovieAlreadyExistsException)}
     */
    @Test
    void testHandleMovieAlreadyExistsException() {
        MovieAppExceptionHandler movieAppExceptionHandler = new MovieAppExceptionHandler();
        ResponseEntity<String> actualHandleMovieAlreadyExistsExceptionResult = movieAppExceptionHandler
                .handleMovieAlreadyExistsException(new MovieAlreadyExistsException("An error occurred"));
        assertEquals("You are trying to add an already existing movie",
                actualHandleMovieAlreadyExistsExceptionResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleMovieAlreadyExistsExceptionResult.getStatusCode());
        assertTrue(actualHandleMovieAlreadyExistsExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link MovieAppExceptionHandler#handleUserAlreadyExistsException(UserAlreadyExistsException)}
     */
    @Test
    void testHandleUserAlreadyExistsException() {
        MovieAppExceptionHandler movieAppExceptionHandler = new MovieAppExceptionHandler();
        ResponseEntity<String> actualHandleUserAlreadyExistsExceptionResult = movieAppExceptionHandler
                .handleUserAlreadyExistsException(new UserAlreadyExistsException("An error occurred"));
        assertEquals("login ID already registered, try signing in",
                actualHandleUserAlreadyExistsExceptionResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleUserAlreadyExistsExceptionResult.getStatusCode());
        assertTrue(actualHandleUserAlreadyExistsExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link MovieAppExceptionHandler#handleUnregisteredUserException(UnregisteredUserException)}
     */
    @Test
    void testHandleUnregisteredUserException() {
        MovieAppExceptionHandler movieAppExceptionHandler = new MovieAppExceptionHandler();
        ResponseEntity<String> actualHandleUnregisteredUserExceptionResult = movieAppExceptionHandler
                .handleUnregisteredUserException(new UnregisteredUserException("An error occurred"));
        assertEquals("User not registered, try signing up with us",
                actualHandleUnregisteredUserExceptionResult.getBody());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleUnregisteredUserExceptionResult.getStatusCode());
        assertTrue(actualHandleUnregisteredUserExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link MovieAppExceptionHandler#handleInvalidPasswordException(InvalidPasswordException)}
     */
    @Test
    void testHandleInvalidPasswordException() {
        MovieAppExceptionHandler movieAppExceptionHandler = new MovieAppExceptionHandler();
        ResponseEntity<String> actualHandleInvalidPasswordExceptionResult = movieAppExceptionHandler
                .handleInvalidPasswordException(new InvalidPasswordException("An error occurred"));
        assertEquals("Invalid Password, consider resetting it", actualHandleInvalidPasswordExceptionResult.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualHandleInvalidPasswordExceptionResult.getStatusCode());
        assertTrue(actualHandleInvalidPasswordExceptionResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link MovieAppExceptionHandler#handleUserNotFoundException(UserNotFoundException)}
     */
    @Test
    void testHandleUserNotFoundException() {
        MovieAppExceptionHandler movieAppExceptionHandler = new MovieAppExceptionHandler();
        ResponseEntity<String> actualHandleUserNotFoundExceptionResult = movieAppExceptionHandler
                .handleUserNotFoundException(new UserNotFoundException("An error occurred"));
        assertEquals("User not found to reset password", actualHandleUserNotFoundExceptionResult.getBody());
        assertEquals(HttpStatus.NOT_FOUND, actualHandleUserNotFoundExceptionResult.getStatusCode());
        assertTrue(actualHandleUserNotFoundExceptionResult.getHeaders().isEmpty());
    }
}

