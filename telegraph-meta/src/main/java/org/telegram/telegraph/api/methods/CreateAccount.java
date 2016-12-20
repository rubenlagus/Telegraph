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
 * Use this method to create a new Telegraph account. Most users only need one account,
 * but this can be useful for channel administrators who would like to keep individual
 * author names and links for each of their channels. On success, returns an Account
 * object with default fields and an additional access_token field.
 */
public class CreateAccount extends TelegraphMethod<Account> {
    private static final String URL_PATH = "createAccount";

    private static final String SHORT_NAME_FIELD = "short_name";
    private static final String AUTHOR_NAME_FIELD = "author_name";
    private static final String AUTHOR_URL_FIELD = "author_url";

    @JsonProperty(SHORT_NAME_FIELD)
    private String shortName; ///< Required (1-32 characters). Account name, helps users with several accounts remember which they are currently using. Displayed to the user above the "Edit/Publish" button on Telegra.ph, other users don't see this name.
    @JsonProperty(AUTHOR_NAME_FIELD)
    private String authorName; ///< (0-128 characters) Default author name for new articles, displayed below the title.
    @JsonProperty(AUTHOR_URL_FIELD)
    private String authorUrl; ///< (0-512 characters) Default author link, opened when users clicks on the name below the title.

    public CreateAccount(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public CreateAccount setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public CreateAccount setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public CreateAccount setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
        return this;
    }

    @Override
    public void validate() throws TelegraphValidationException {
        if (shortName == null || shortName.length() < 1 || shortName.length() > 32) {
            throw new TelegraphValidationException("Wrong parameter Short Name", this);
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
                throw new TelegraphRequestException("Error creating account", result);
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
        return "CreateAccount{" +
                "shortName='" + shortName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorUrl='" + authorUrl + '\'' +
                '}';
    }
}
