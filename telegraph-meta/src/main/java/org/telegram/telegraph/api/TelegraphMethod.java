package org.telegram.telegraph.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegraph.TelegraphContext;
import org.telegram.telegraph.exceptions.TelegraphException;
import org.telegram.telegraph.exceptions.TelegraphRequestException;
import org.telegram.telegraph.executors.TelegraphExecutorFactory;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class TelegraphMethod<T extends TelegraphObject> implements Validable {
    @JsonIgnore
    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Deserialize a json answer to the response type to a method
     * @param answer Json answer received
     * @return Answer for the method
     */
    public abstract T deserializeResponse(String answer) throws TelegraphRequestException;

    /**
     * Return the path to perform Https request
     * @return Path that needs to be appended to the base url
     */
    @JsonIgnore
    public abstract String getUrlPath();

    /**
     * Execute this method directly
     * @return Result of method execution
     * @throws TelegraphException If request or validation fails.
     */
    public T execute() throws TelegraphException {
        validate();
        return TelegraphContext.getInstance(TelegraphExecutorFactory.class)
                .getExecutor().execute(this);
    }
}
