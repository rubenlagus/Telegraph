package org.telegram.telegraph.api.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.telegram.telegraph.api.TelegraphObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * This object represents a Telegraph account.
 */
public class Account implements TelegraphObject {
    private static final String SHORT_NAME_FIELD = "short_name";
    private static final String AUTHOR_NAME_FIELD = "author_name";
    private static final String AUTHOR_URL_FIELD = "author_url";
    private static final String ACCESS_TOKEN_FIELD = "access_token";
    private static final String AUTH_URL_FIELD = "auth_url";
    private static final String PAGE_COUNT_FIELD = "page_count";

    @JsonProperty(SHORT_NAME_FIELD)
    private String shortName; ///< Account name, helps users with several accounts remember which they are currently using. Displayed to the user above the "Edit/Publish" button on Telegra.ph, other users don't see this name.
    @JsonProperty(AUTHOR_NAME_FIELD)
    private String authorName; ///< Author name displayed below the title.
    @JsonProperty(AUTHOR_URL_FIELD)
    private String authorUrl; ///< URL to be opened when a user clicks on the name below the title.
    @JsonProperty(ACCESS_TOKEN_FIELD)
    private String accessToken; ///< Optional. Only returned by the createAccount and revokeAccessToken method. Access token of the Telegraph account.
    @JsonProperty(AUTH_URL_FIELD)
    private String authUrl; ///< Optional. URL to authorize a browser on telegra.ph and connect it to a Telegraph account. This URL is valid for only one use and for 5 minutes only.
    @JsonProperty(PAGE_COUNT_FIELD)
    private Integer pageCount; ///< Optional. Number of pages belonging to the Telegraph account.

    public Account() {
    }

    public String getShortName() {
        return shortName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "shortName='" + shortName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorUrl='" + authorUrl + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", authUrl='" + authUrl + '\'' +
                ", pageCount=" + pageCount +
                '}';
    }
}
