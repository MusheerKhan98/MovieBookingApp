package com.moviebookingapp.custom.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class MovieAlreadyExistsExceptionTest {
    /**
     * Method under test: {@link MovieAlreadyExistsException#MovieAlreadyExistsException()}
     */
    @Test
    void testConstructor() {
        MovieAlreadyExistsException actualMovieAlreadyExistsException = new MovieAlreadyExistsException();
        assertNull(actualMovieAlreadyExistsException.getCause());
        assertEquals(0, actualMovieAlreadyExistsException.getSuppressed().length);
        assertNull(actualMovieAlreadyExistsException.getMessage());
        assertNull(actualMovieAlreadyExistsException.getLocalizedMessage());
    }

    /**
     * Method under test: {@link MovieAlreadyExistsException#MovieAlreadyExistsException(String)}
     */
    @Test
    void testConstructor2() {
        MovieAlreadyExistsException actualMovieAlreadyExistsException = new MovieAlreadyExistsException(
                "An error occurred");
        assertNull(actualMovieAlreadyExistsException.getCause());
        assertEquals(0, actualMovieAlreadyExistsException.getSuppressed().length);
        assertEquals("An error occurred", actualMovieAlreadyExistsException.getMessage());
        assertEquals("An error occurred", actualMovieAlreadyExistsException.getLocalizedMessage());
    }
}

