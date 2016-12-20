package org.telegram.telegraph.api.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.telegram.telegraph.api.TelegraphObject;

import java.util.List;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * This object represents a list of Telegraph articles belonging to an account. Most recently edited articles first.
 */
public class PageList implements TelegraphObject {
    private static final String TOTAL_COUNT_FIELD = "total_count";
    private static final String PAGES_FIELD = "pages";

    @JsonProperty(TOTAL_COUNT_FIELD)
    private Integer totalCount; ///< Total number of pages belonging to the target Telegraph account.
    @JsonProperty(PAGES_FIELD)
    private List<Page> pages; ///< Requested pages of the target Telegraph account.

    public PageList() {
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public List<Page> getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "PageList{" +
                "totalCount=" + totalCount +
                ", pages=" + pages +
                '}';
    }
}
