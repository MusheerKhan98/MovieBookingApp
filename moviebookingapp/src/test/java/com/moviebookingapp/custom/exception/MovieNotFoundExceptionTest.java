package com.moviebookingapp.custom.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class MovieNotFoundExceptionTest {
    /**
     * Method under test: {@link MovieNotFoundException#MovieNotFoundException()}
     */
    @Test
    void testConstructor() {
        MovieNotFoundException actualMovieNotFoundException = new MovieNotFoundException();
        assertNull(actualMovieNotFoundException.getCause());
        assertEquals(0, actualMovieNotFoundException.getSuppressed().length);
        assertNull(actualMovieNotFoundException.getMessage());
        assertNull(actualMovieNotFoundException.getLocalizedMessage());
    }

    /**
     * Method under test: {@link MovieNotFoundException#MovieNotFoundException(String)}
     */
    @Test
    void testConstructor2() {
        MovieNotFoundException actualMovieNotFoundException = new MovieNotFoundException("An error occurred");
        assertNull(actualMovieNotFoundException.getCause());
        assertEquals(0, actualMovieNotFoundException.getSuppressed().length);
        assertEquals("An error occurred", actualMovieNotFoundException.getMessage());
        assertEquals("An error occurred", actualMovieNotFoundException.getLocalizedMessage());
    }
}

