package org.telegram.telegraph.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import org.telegram.telegraph.api.TelegraphMethod;
import org.telegram.telegraph.api.objects.Node;
import org.telegram.telegraph.api.objects.Page;
import org.telegram.telegraph.api.objects.TelegraphResponse;
import org.telegram.telegraph.exceptions.TelegraphRequestException;
import org.telegram.telegraph.exceptions.TelegraphValidationException;

import java.io.IOException;
import java.util.List;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * Use this method to edit an existing Telegraph page.
 * On success, returns a Page object.
 */
public class EditPage extends TelegraphMethod<Page> {
    private static final String URL_PATH = "editPage";

    private static final String ACCESS_TOKEN_FIELD = "access_token";
    private static final String PATH_FIELD = "path";
    private static final String TITLE_FIELD = "title";
    private static final String CONTENT_FIELD = "content";
    private static final String AUTHOR_NAME_FIELD = "author_name";
    private static final String AUTHOR_URL_FIELD = "author_url";
    private static final String RETURN_CONTENT_FIELD = "return_content";

    @JsonProperty(ACCESS_TOKEN_FIELD)
    private String accessToken; ///< Required. Access token of the Telegraph account.
    @JsonProperty(PATH_FIELD)
    private String path; ///< Required. Path to the page.
    @JsonProperty(TITLE_FIELD)
    private String title; ///< Required (1-256 characters). Page title.
    @JsonProperty(CONTENT_FIELD)
    private List<Node> content; ///< Required (Up to 64KB). Content of the page.
    @JsonProperty(AUTHOR_NAME_FIELD)
    private String authorName; ///< (0-128 characters) Default author name for new articles, displayed below the title.
    @JsonProperty(AUTHOR_URL_FIELD)
    private String authorUrl; ///< (0-512 characters) Default author link, opened when users clicks on the name below the title.
    @JsonProperty(RETURN_CONTENT_FIELD)
    private Boolean returnContent; ///< If true, a content field will be returned in the Page object (see: Content format).

    public EditPage(String accessToken, String path, String title, List<Node> content) {
        this.accessToken = accessToken;
        this.path = path;
        this.title = title;
        this.content = content;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public EditPage setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getPath() {
        return path;
    }

    public EditPage setPath(String path) {
        this.path = path;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public EditPage setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<Node> getContent() {
        return content;
    }

    public EditPage setContent(List<Node> content) {
        this.content = content;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public EditPage setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public EditPage setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
        return this;
    }

    public Boolean getReturnContent() {
        return returnContent;
    }

    public EditPage setReturnContent(Boolean returnContent) {
        this.returnContent = returnContent;
        return this;
    }

    @Override
    public void validate() throws TelegraphValidationException {
        if (accessToken == null || accessToken.isEmpty()) {
            throw new TelegraphValidationException("Parameter Access token is required", this);
        }
        if (path == null || path.isEmpty()) {
            throw new TelegraphValidationException("Parameter Path field is required", this);
        }
        if (title == null || title.length() < 1 || title.length() > 256) {
            throw new TelegraphValidationException("Parameter Title is missing or out of size", this);
        }
        if (content == null || content.isEmpty()) {
            throw new TelegraphValidationException("Parameter Content is required", this);
        }
        if (authorName != null && (authorName.length() < 0 || authorName.length() > 128)) {
            throw new TelegraphValidationException("Wrong size for Author Name", this);
        }
        if (authorUrl != null && (authorUrl.length() < 0 || authorUrl.length() > 512)) {
            throw new TelegraphValidationException("Wrong size for Author Url", this);
        }
    }

    @Override
    public Page deserializeResponse(String answer) throws TelegraphRequestException {
        try {
            TelegraphResponse<Page> result = OBJECT_MAPPER.readValue(answer,
                    new TypeReference<TelegraphResponse<Page>>(){});
            if (result.getOk()) {
                return result.getResult();
            } else {
                throw new TelegraphRequestException("Error editing page", result);
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
        return "EditPage{" +
                "accessToken='" + accessToken + '\'' +
                ", path='" + path + '\'' +
                ", title='" + title + '\'' +
                ", content=" + content +
                ", authorName='" + authorName + '\'' +
                ", authorUrl='" + authorUrl + '\'' +
                ", returnContent=" + returnContent +
                '}';
    }
}
