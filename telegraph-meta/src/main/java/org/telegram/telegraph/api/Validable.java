package org.telegram.telegraph.api;

import org.telegram.telegraph.exceptions.TelegraphValidationException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public interface Validable {
    /**
     * Validates that mandatory fields are filled and optional objects
     * @throws TelegraphValidationException If any mandatory field is invalid
     */
    void validate() throws TelegraphValidationException;
}
