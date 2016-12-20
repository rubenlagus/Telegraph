package org.telegram.telegraph.executors;

import org.telegram.telegraph.api.TelegraphMethod;
import org.telegram.telegraph.api.TelegraphObject;
import org.telegram.telegraph.exceptions.TelegraphException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * Base interface to execute a method, support any custom implementation. Library will create instances of this class
 * via TelegraphExecutorFactory when necessary.
 */
public interface TelegraphExecutor {
    /**
     * Executes a method and returns its result
     * @param method Method to execute
     * @param <T> Type of method result
     * @return Results of the method
     * @throws TelegraphException If validation or requests fails
     */
    <T extends TelegraphObject> T execute(TelegraphMethod<T> method) throws TelegraphException;
}
