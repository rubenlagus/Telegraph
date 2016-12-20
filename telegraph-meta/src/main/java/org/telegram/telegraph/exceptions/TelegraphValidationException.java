package org.telegram.telegraph.exceptions;

import org.telegram.telegraph.api.TelegraphMethod;
import org.telegram.telegraph.api.TelegraphObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TelegraphValidationException extends TelegraphException {
    private TelegraphMethod method;
    private TelegraphObject object;

    public TelegraphValidationException(String message, TelegraphMethod method) {
        super(message);
        this.method = method;
    }

    public TelegraphValidationException(String message, TelegraphObject object) {
        super(message);
        this.object = object;
    }

    public TelegraphMethod getMethod() {
        return method;
    }

    public TelegraphObject getObject() {
        return object;
    }

    @Override
    public String toString() {
        if (method != null) {
            return super.toString() + " in method: " + method.toString();
        }
        if (object != null) {
            return super.toString() + " in object: " + object.toString();
        }
        return super.toString();
    }
}
