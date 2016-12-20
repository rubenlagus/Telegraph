package org.telegram.telegraph.api.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class TelegraphResponse<T> implements Serializable {
    private static final String OK_FIELD = "ok";
    private static final String ERROR_FIELD = "error";
    private static final String RESULT_FIELD = "result";

    @JsonProperty(OK_FIELD)
    private Boolean ok;
    @JsonProperty(ERROR_FIELD)
    private String error;
    @JsonProperty(RESULT_FIELD)
    private T result;

    public Boolean getOk() {
        return ok;
    }

    public String getError() {
        return error;
    }

    public T getResult() {
        return result;
    }

    @Override
    public String toString() {
        if (ok) {
            return "TelegraphResponse{" +
                    "ok=" + ok +
                    ", result=" + result +
                    '}';
        } else {
            return "TelegraphResponse{" +
                    "ok=" + ok +
                    ", error=" + error +
                    '}';
        }
    }

}
