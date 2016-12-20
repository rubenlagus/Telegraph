package org.telegram.telegraph.executors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.telegram.telegraph.ExecutorOptions;
import org.telegram.telegraph.TelegraphConstants;
import org.telegram.telegraph.api.TelegraphMethod;
import org.telegram.telegraph.api.TelegraphObject;
import org.telegram.telegraph.exceptions.TelegraphException;
import org.telegram.telegraph.exceptions.TelegraphRequestException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author Ruben Bermudez
 * @version 1.0
 */
public class DefaultTelegraphExecutor implements TelegraphExecutor {
    private final ObjectMapper objectMapper;
    private volatile CloseableHttpClient httpclient;
    private volatile RequestConfig requestConfig;

    @Inject
    public DefaultTelegraphExecutor(ExecutorOptions options) {
        this.objectMapper = new ObjectMapper();
        httpclient = HttpClientBuilder.create()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .setConnectionTimeToLive(70, TimeUnit.SECONDS)
                .setMaxConnTotal(100)
                .build();

        requestConfig = options.getRequestConfig();
    }

    @Override
    public <T extends TelegraphObject> T execute(TelegraphMethod<T> method) throws TelegraphException {
        String responseContent;
        try {
            String url = TelegraphConstants.BASE_URL + method.getUrlPath();
            HttpPost httppost = new HttpPost(url);
            httppost.setConfig(requestConfig);
            httppost.addHeader("charset", StandardCharsets.UTF_8.name());
            httppost.addHeader("Content-Type", "application/json");
            httppost.setEntity(new StringEntity(objectMapper.writeValueAsString(method), ContentType.APPLICATION_JSON));
            try (CloseableHttpResponse response = httpclient.execute(httppost)) {
                HttpEntity ht = response.getEntity();
                BufferedHttpEntity buf = new BufferedHttpEntity(ht);
                responseContent = EntityUtils.toString(buf, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            throw new TelegraphRequestException("Unable to execute " + method.getUrlPath() + " method", e);
        }

        return method.deserializeResponse(responseContent);
    }
}
