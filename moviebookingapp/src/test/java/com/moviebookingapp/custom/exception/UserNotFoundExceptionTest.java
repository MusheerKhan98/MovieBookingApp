package com.moviebookingapp.custom.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class UserNotFoundExceptionTest {
    /**
     * Method under test: {@link UserNotFoundException#UserNotFoundException()}
     */
    @Test
    void testConstructor() {
        UserNotFoundException actualUserNotFoundException = new UserNotFoundException();
        assertNull(actualUserNotFoundException.getCause());
        assertEquals(0, actualUserNotFoundException.getSuppressed().length);
        assertNull(actualUserNotFoundException.getMessage());
        assertNull(actualUserNotFoundException.getLocalizedMessage());
    }

    /**
     * Method under test: {@link UserNotFoundException#UserNotFoundException(String)}
     */
    @Test
    void testConstructor2() {
        UserNotFoundException actualUserNotFoundException = new UserNotFoundException("An error occurred");
        assertNull(actualUserNotFoundException.getCause());
        assertEquals(0, actualUserNotFoundException.getSuppressed().length);
        assertEquals("An error occurred", actualUserNotFoundException.getMessage());
        assertEquals("An error occurred", actualUserNotFoundException.getLocalizedMessage());
    }
}

