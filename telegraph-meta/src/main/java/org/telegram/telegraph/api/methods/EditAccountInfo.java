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
 * Use this method to update information about a Telegraph account.
 * Pass only the parameters that you want to edit. On success,
 * returns an Account object with the default fields.
 */
public class EditAccountInfo extends TelegraphMethod<Account> {
    private static final String URL_PATH = "editAccountInfo";

    private static final String ACCESS_TOKEN_FIELD = "access_token";
    private static final String SHORT_NAME_FIELD = "short_name";
    private static final String AUTHOR_NAME_FIELD = "author_name";
    private static final String AUTHOR_URL_FIELD = "author_url";

    @JsonProperty(ACCESS_TOKEN_FIELD)
    private String accessToken; ///< Required. Access token of the Telegraph account.
    @JsonProperty(SHORT_NAME_FIELD)
    private String shortName; ///< (1-32 characters). New account name.
    @JsonProperty(AUTHOR_NAME_FIELD)
    private String authorName; ///< (0-128 characters) New default author name used when creating new articles.
    @JsonProperty(AUTHOR_URL_FIELD)
    private String authorUrl; ///< (0-512 characters) New default profile link, opened when users click on the author's name below the title. Can be any link, not necessarily to a Telegram profile or channel.

    public EditAccountInfo(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public EditAccountInfo setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getShortName() {
        return shortName;
    }

    public EditAccountInfo setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public EditAccountInfo setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public EditAccountInfo setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
        return this;
    }

    @Override
    public void validate() throws TelegraphValidationException {
        if (accessToken == null || accessToken.isEmpty()) {
            throw new TelegraphValidationException("Parameter Access token is required", this);
        }
        if (shortName != null && (shortName.length() < 1 || shortName.length() > 32)) {
            throw new TelegraphValidationException("Wrong size for Short Name", this);
        }
        if (authorName != null && (authorName.length() < 0 || authorName.length() > 128)) {
            throw new TelegraphValidationException("Wrong size for Author Name", this);
        }
        if (authorUrl != null && (authorUrl.length() < 0 || authorUrl.length() > 512)) {
            throw new TelegraphValidationException("Wrong size for Author Url", this);
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
                throw new TelegraphRequestException("Error editing account", result);
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
        return "EditAccountInfo{" +
                "accessToken='" + accessToken + '\'' +
                ", shortName='" + shortName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorUrl='" + authorUrl + '\'' +
                '}';
    }
}
