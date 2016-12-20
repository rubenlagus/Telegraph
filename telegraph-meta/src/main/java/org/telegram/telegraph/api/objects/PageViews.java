package org.telegram.telegraph.api.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.telegram.telegraph.api.TelegraphObject;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class PageViews implements TelegraphObject {
    private static final String VIEWS_FIELD = "views";

    @JsonProperty(VIEWS_FIELD)
    private Integer views; ///< Number of page views for the target page.

    public PageViews() {
    }

    public Integer getViews() {
        return views;
    }

    @Override
    public String toString() {
        return "PageViews{" +
                "views=" + views +
                '}';
    }
}
