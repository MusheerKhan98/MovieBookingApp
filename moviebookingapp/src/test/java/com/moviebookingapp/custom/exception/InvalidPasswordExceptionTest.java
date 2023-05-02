package com.moviebookingapp.custom.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class InvalidPasswordExceptionTest {
    /**
     * Method under test: {@link InvalidPasswordException#InvalidPasswordException()}
     */
    @Test
    void testConstructor() {
        InvalidPasswordException actualInvalidPasswordException = new InvalidPasswordException();
        assertNull(actualInvalidPasswordException.getCause());
        assertEquals(0, actualInvalidPasswordException.getSuppressed().length);
        assertNull(actualInvalidPasswordException.getMessage());
        assertNull(actualInvalidPasswordException.getLocalizedMessage());
    }

    /**
     * Method under test: {@link InvalidPasswordException#InvalidPasswordException(String)}
     */
    @Test
    void testConstructor2() {
        InvalidPasswordException actualInvalidPasswordException = new InvalidPasswordException("An error occurred");
        assertNull(actualInvalidPasswordException.getCause());
        assertEquals(0, actualInvalidPasswordException.getSuppressed().length);
        assertEquals("An error occurred", actualInvalidPasswordException.getMessage());
        assertEquals("An error occurred", actualInvalidPasswordException.getLocalizedMessage());
    }
}

