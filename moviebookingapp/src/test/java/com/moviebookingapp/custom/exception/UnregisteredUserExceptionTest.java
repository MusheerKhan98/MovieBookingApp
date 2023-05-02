package com.moviebookingapp.custom.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class UnregisteredUserExceptionTest {
    /**
     * Method under test: {@link UnregisteredUserException#UnregisteredUserException()}
     */
    @Test
    void testConstructor() {
        UnregisteredUserException actualUnregisteredUserException = new UnregisteredUserException();
        assertNull(actualUnregisteredUserException.getCause());
        assertEquals(0, actualUnregisteredUserException.getSuppressed().length);
        assertNull(actualUnregisteredUserException.getMessage());
        assertNull(actualUnregisteredUserException.getLocalizedMessage());
    }

    /**
     * Method under test: {@link UnregisteredUserException#UnregisteredUserException(String)}
     */
    @Test
    void testConstructor2() {
        UnregisteredUserException actualUnregisteredUserException = new UnregisteredUserException("An error occurred");
        assertNull(actualUnregisteredUserException.getCause());
        assertEquals(0, actualUnregisteredUserException.getSuppressed().length);
        assertEquals("An error occurred", actualUnregisteredUserException.getMessage());
        assertEquals("An error occurred", actualUnregisteredUserException.getLocalizedMessage());
    }
}

