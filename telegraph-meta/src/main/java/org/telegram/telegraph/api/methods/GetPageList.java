package org.telegram.telegraph.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import org.telegram.telegraph.api.TelegraphMethod;
import org.telegram.telegraph.api.objects.PageList;
import org.telegram.telegraph.api.objects.TelegraphResponse;
import org.telegram.telegraph.exceptions.TelegraphRequestException;
import org.telegram.telegraph.exceptions.TelegraphValidationException;

import java.io.IOException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * Use this method to get a list of pages belonging to a Telegraph account.
 * Returns a PageList object, sorted by most recently created pages first.
 */
public class GetPageList extends TelegraphMethod<PageList> {
    private static final String URL_PATH = "getPageList";

    private static final String ACCESS_TOKEN_FIELD = "access_token";
    private static final String OFFSET_FIELD = "offset";
    private static final String LIMIT_FIELD = "limit";

    @JsonProperty(ACCESS_TOKEN_FIELD)
    private String accessToken; ///< Required. Access token of the Telegraph account
    @JsonProperty(OFFSET_FIELD)
    private Integer offset; ///< (Default 0) Sequential number of the first page to be returned.
    @JsonProperty(LIMIT_FIELD)
    private Integer limit; ///< (Default 50, 0-200) Limits the number of pages to be retrieved.

    public GetPageList(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public GetPageList setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public Integer getOffset() {
        return offset;
    }

    public GetPageList setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public GetPageList setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public void validate() throws TelegraphValidationException {
        if (accessToken == null || accessToken.isEmpty()) {
            throw new TelegraphValidationException("Parameter Access token is required", this);
        }
        if (offset != null && (offset < 0 || offset > 200)) {
            throw new TelegraphValidationException("Parameter offset out of range", this);
        }
    }

    @Override
    public PageList deserializeResponse(String answer) throws TelegraphRequestException {
        try {
            TelegraphResponse<PageList> result = OBJECT_MAPPER.readValue(answer,
                    new TypeReference<TelegraphResponse<PageList>>(){});
            if (result.getOk()) {
                return result.getResult();
            } else {
                throw new TelegraphRequestException("Error getting page list", result);
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
        return "GetPageList{" +
                "accessToken='" + accessToken + '\'' +
                ", offset=" + offset +
                ", limit=" + limit +
                '}';
    }
}
