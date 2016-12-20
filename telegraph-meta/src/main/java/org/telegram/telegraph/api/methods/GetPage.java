package org.telegram.telegraph.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import org.telegram.telegraph.api.TelegraphMethod;
import org.telegram.telegraph.api.objects.Page;
import org.telegram.telegraph.api.objects.TelegraphResponse;
import org.telegram.telegraph.exceptions.TelegraphRequestException;
import org.telegram.telegraph.exceptions.TelegraphValidationException;

import java.io.IOException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * Use this method to get a Telegraph page.
 * Returns a Page object on success.
 */
public class GetPage extends TelegraphMethod<Page> {
    private static final String URL_PATH = "getPage";

    private static final String PATH_FIELD = "path";
    private static final String RETURN_CONTENT_FIELD = "return_content";

    @JsonProperty(PATH_FIELD)
    private String path; ///< Required. Path to the Telegraph page (in the format Title-12-31, i.e. everything that comes after http://telegra.ph/).
    @JsonProperty(RETURN_CONTENT_FIELD)
    private Boolean returnContent; ///< If true, content field will be returned in Page object.

    public GetPage(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public GetPage setPath(String path) {
        this.path = path;
        return this;
    }

    public Boolean getReturnContent() {
        return returnContent;
    }

    public GetPage setReturnContent(Boolean returnContent) {
        this.returnContent = returnContent;
        return this;
    }

    @Override
    public void validate() throws TelegraphValidationException {
        if (path == null || path.isEmpty()) {
            throw new TelegraphValidationException("Parameter Path field is required", this);
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
                throw new TelegraphRequestException("Error getting page", result);
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
        return "GetPage{" +
                "path='" + path + '\'' +
                ", returnContent=" + returnContent +
                '}';
    }
}
