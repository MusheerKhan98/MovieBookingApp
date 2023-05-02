package com.moviebookingapp.custom.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class UserAlreadyExistsExceptionTest {
    /**
     * Method under test: {@link UserAlreadyExistsException#UserAlreadyExistsException()}
     */
    @Test
    void testConstructor() {
        UserAlreadyExistsException actualUserAlreadyExistsException = new UserAlreadyExistsException();
        assertNull(actualUserAlreadyExistsException.getCause());
        assertEquals(0, actualUserAlreadyExistsException.getSuppressed().length);
        assertNull(actualUserAlreadyExistsException.getMessage());
        assertNull(actualUserAlreadyExistsException.getLocalizedMessage());
    }

    /**
     * Method under test: {@link UserAlreadyExistsException#UserAlreadyExistsException(String)}
     */
    @Test
    void testConstructor2() {
        UserAlreadyExistsException actualUserAlreadyExistsException = new UserAlreadyExistsException("An error occurred");
        assertNull(actualUserAlreadyExistsException.getCause());
        assertEquals(0, actualUserAlreadyExistsException.getSuppressed().length);
        assertEquals("An error occurred", actualUserAlreadyExistsException.getMessage());
        assertEquals("An error occurred", actualUserAlreadyExistsException.getLocalizedMessage());
    }
}

