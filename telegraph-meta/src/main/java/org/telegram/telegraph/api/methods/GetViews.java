package org.telegram.telegraph.api.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import org.telegram.telegraph.api.TelegraphMethod;
import org.telegram.telegraph.api.objects.PageViews;
import org.telegram.telegraph.api.objects.TelegraphResponse;
import org.telegram.telegraph.exceptions.TelegraphRequestException;
import org.telegram.telegraph.exceptions.TelegraphValidationException;

import java.io.IOException;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * Use this method to get the number of views for a Telegraph article.
 * Returns a PageViews object on success.
 * By default, the total number of page views will be returned.
 */
public class GetViews extends TelegraphMethod<PageViews> {
    private static final String URL_PATH = "getViews";

    private static final String PATH_FIELD = "path";
    private static final String YEAR_FIELD = "year";
    private static final String MONTH_FIELD = "month";
    private static final String DAY_FIELD = "day";
    private static final String HOUR_FIELD = "hour";

    @JsonProperty(PATH_FIELD)
    private String path; ///< Required. Path to the Telegraph page (in the format Title-12-31, where 12 is the month and 31 the day the article was first published).
    @JsonProperty(YEAR_FIELD)
    private Integer year; ///< (2000-2100) Required if month is passed. If passed, the number of page views for the requested year will be returned.
    @JsonProperty(MONTH_FIELD)
    private Integer month; ///< (1-12) Required if day is passed. If passed, the number of page views for the requested month will be returned.
    @JsonProperty(DAY_FIELD)
    private Integer day; ///< (1-31) Required if hour is passed. If passed, the number of page views for the requested day will be returned.
    @JsonProperty(HOUR_FIELD)
    private Integer hour; ///< (0-24) If passed, the number of page views for the requested hour will be returned.

    public GetViews(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public GetViews setPath(String path) {
        this.path = path;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public GetViews setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Integer getMonth() {
        return month;
    }

    public GetViews setMonth(Integer month) {
        this.month = month;
        return this;
    }

    public Integer getDay() {
        return day;
    }

    public GetViews setDay(Integer day) {
        this.day = day;
        return this;
    }

    public Integer getHour() {
        return hour;
    }

    public GetViews setHour(Integer hour) {
        this.hour = hour;
        return this;
    }

    @Override
    public void validate() throws TelegraphValidationException {
        if (path == null || path.isEmpty()) {
            throw new TelegraphValidationException("Parameter Path is required", this);
        }
        if (year != null && (year < 2000 || year > 2100)) {
            throw new TelegraphValidationException("Parameter year out of range", this);
        }
        if (month != null && year == null) {
            throw new TelegraphValidationException("Parameter year is required when month is present", this);
        }
        if (month != null && (month < 1 || month > 12)) {
            throw new TelegraphValidationException("Parameter month out of range", this);
        }
        if (day != null && month == null) {
            throw new TelegraphValidationException("Parameter month is required when day is present", this);
        }
        if (day != null && (day < 1 || day > 31)) {
            throw new TelegraphValidationException("Parameter day out of range", this);
        }
        if (hour != null && day == null) {
            throw new TelegraphValidationException("Parameter day is required when hour is present", this);
        }
        if (hour != null && (hour < 0 || hour > 24)) {
            throw new TelegraphValidationException("Parameter hour out of range", this);
        }
    }

    @Override
    public PageViews deserializeResponse(String answer) throws TelegraphRequestException {
        try {
            TelegraphResponse<PageViews> result = OBJECT_MAPPER.readValue(answer,
                    new TypeReference<TelegraphResponse<PageViews>>(){});
            if (result.getOk()) {
                return result.getResult();
            } else {
                throw new TelegraphRequestException("Error getting views", result);
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
        return "GetViews{" +
                "path='" + path + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                '}';
    }
}
