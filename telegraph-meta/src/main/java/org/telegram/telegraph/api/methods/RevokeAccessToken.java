package org.telegram.telegraph.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import org.telegram.telegraph.api.TelegraphMethod;
import org.telegram.telegraph.api.objects.Account;
import org.telegram.telegraph.api.objects.TelegraphResponse;
import org.telegram.telegraph.exceptions.TelegraphRequestException;
import org.telegram.telegraph.exceptions.TelegraphValidationException;

import java.io.IOException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * Use this method to revoke access_token and generate a new one, for example,
 * if the user would like to reset all connected sessions,
 * or you have reasons to believe the token was compromised.
 * On success, returns an Account object with new access_token and auth_url fields.
 */
public class RevokeAccessToken extends TelegraphMethod<Account> {
    private static final String URL_PATH = "revokeAccessToken";

    private static final String ACCESS_TOKEN_FIELD = "access_token";

    @JsonProperty(ACCESS_TOKEN_FIELD)
    private String accessToken; ///< Required. Access token of the Telegraph account.

    public RevokeAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public RevokeAccessToken setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    @Override
    public void validate() throws TelegraphValidationException {
        if (accessToken == null || accessToken.isEmpty()) {
            throw new TelegraphValidationException("Parameter Access token is required", this);
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
                throw new TelegraphRequestException("Error revoking token", result);
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
        return "RevokeAccessToken{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}
