package org.telegram.telegraph;

import com.google.inject.Inject;
import org.apache.http.client.config.RequestConfig;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class ExecutorOptions {
    private RequestConfig requestConfig;

    @Inject
    public ExecutorOptions() {
    }

    public RequestConfig getRequestConfig() {
        return requestConfig;
    }

    /**
     * @implSpec Default implementation assumes no proxy is needed and sets a 75secs timeout
     * @param requestConfig Request config to be used in all Http requests
     */
    public void setRequestConfig(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
    }
}
