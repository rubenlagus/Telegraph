package org.telegram.telegraph.api.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.telegram.telegraph.api.TelegraphObject;

import java.util.List;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * This object represents a page on Telegraph.
 */
public class Page implements TelegraphObject {
    private static final String PATH_FIELD = "path";
    private static final String URL_FIELD = "url";
    private static final String TITLE_FIELD = "title";
    private static final String DESCRIPTION_FIELD = "description";
    private static final String AUTHOR_NAME_FIELD = "author_name";
    private static final String AUTHOR_URL_FIELD = "author_url";
    private static final String IMAGE_URL_FIELD = "image_url";
    private static final String CONTENT_FIELD = "content";
    private static final String CAN_EDIT_FIELD = "can_edit";

    @JsonProperty(PATH_FIELD)
    private String path; ///< Path to the page.
    @JsonProperty(URL_FIELD)
    private String url; ///< URL of the page.
    @JsonProperty(TITLE_FIELD)
    private String title; ///< Title of the page.
    @JsonProperty(DESCRIPTION_FIELD)
    private String description; ///< Description of the page.
    @JsonProperty(AUTHOR_NAME_FIELD)
    private String authorName; ///< Optional. Name of the author.
    @JsonProperty(AUTHOR_URL_FIELD)
    private String authorUrl; ///< Optional. URL for the author.
    @JsonProperty(IMAGE_URL_FIELD)
    private String imageUrl; ///< Optional. Image URL of the page.
    @JsonProperty(CONTENT_FIELD)
    private List<Node> content; ///< Optional. Content of the page.
    @JsonProperty(CAN_EDIT_FIELD)
    private Boolean canEdit; ///< Optional. Only returned if access_token passed. True, if the target Telegraph account can edit the page.

    public Page() {
    }

    public String getPath() {
        return path;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<Node> getContent() {
        return content;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    @Override
    public String toString() {
        return "Page{" +
                "path='" + path + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorUrl='" + authorUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", content=" + content +
                ", canEdit=" + canEdit +
                '}';
    }
}
