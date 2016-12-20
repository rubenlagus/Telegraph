package org.telegram.telegraph.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import org.telegram.telegraph.api.TelegraphMethod;
import org.telegram.telegraph.api.objects.Account;
import org.telegram.telegraph.api.objects.TelegraphResponse;
import org.telegram.telegraph.exceptions.TelegraphRequestException;
import org.telegram.telegraph.exceptions.TelegraphValidationException;

import java.io.IOException;
import java.util.List;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * Use this method to get information about a Telegraph account.
 * Returns an Account object on success.
 */
public class GetAccountInfo extends TelegraphMethod<Account> {
    private static final String URL_PATH = "getAccountInfo";

    private static final String ACCESS_TOKEN_FIELD = "access_token";
    private static final String FIELDS_FIELD = "fields";

    @JsonProperty(ACCESS_TOKEN_FIELD)
    private String accessToken; ///< Required. Access token of the Telegraph account.
    @JsonProperty(FIELDS_FIELD)
    private List<String> fields; ///< List of account fields to return. Available fields: Check org.telegram.telegraph.api.objects.AccountField

    public GetAccountInfo(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    @Override
    public void validate() throws TelegraphValidationException {
        if (accessToken == null || accessToken.isEmpty()) {
            throw new TelegraphValidationException("Parameter Access token is required", this);
        }
        if (fields != null && fields.isEmpty()) {
            throw new TelegraphValidationException("Fields list is empty", this);
        }
    }

    @Override
    public Account deserializeResponse(String answer) throws TelegraphRequestException {
        try {
            TelegraphResponse<Account> result = OBJECT_MAPPER.readValue(answer,
                    new TypeReference<TelegraphResponse<Account>>(){});
            if (result.getOk()) {
                return result.getResult();
            } else {
                throw new TelegraphRequestException("Error getting account information", result);
            }
        } catch (IOException e) {
            throw new TelegraphRequestException("Unable to deserialize response", e);
        }
    }

    @Override
    public String getUrlPath() {
        return URL_PATH;
    }

    @Override
    public String toString() {
        return "GetAccountInfo{" +
                "accessToken='" + accessToken + '\'' +
                ", fields=" + fields +
                '}';
    }
}
