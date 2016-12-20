package org.telegram.telegraph.exceptions;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public abstract class TelegraphException extends Exception {
    public TelegraphException() {
        super();
    }

    public TelegraphException(String message) {
        super(message);
    }

    public TelegraphException(String message, Throwable cause) {
        super(message, cause);
    }

    public TelegraphException(Throwable cause) {
        super(cause);
    }

    protected TelegraphException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
