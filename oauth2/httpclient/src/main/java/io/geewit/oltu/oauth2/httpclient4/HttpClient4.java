package io.geewit.oltu.oauth2.httpclient4;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import io.geewit.oltu.oauth2.client.HttpClient;
import io.geewit.oltu.oauth2.client.request.OAuthClientRequest;
import io.geewit.oltu.oauth2.client.response.OAuthClientResponse;
import io.geewit.oltu.oauth2.client.response.OAuthClientResponseFactory;
import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Example Oltu HttpClient based on the Apache HttpComponents HttpClient
 *
 *
 *
 *
 */
public class HttpClient4 implements HttpClient {

    private org.apache.http.client.HttpClient client;

    public HttpClient4() {
        client = new DefaultHttpClient();
    }

    public HttpClient4(org.apache.http.client.HttpClient client) {
        this.client = client;
    }

    public void shutdown() {
        if (client != null) {
            ClientConnectionManager connectionManager = client.getConnectionManager();
            if (connectionManager != null) {
                connectionManager.shutdown();
            }
        }
    }

    public <T extends OAuthClientResponse> T execute(OAuthClientRequest request,
                                                     Map<String, String> headers,
                                                     String requestMethod,
                                                     Class<T> responseClass)
            throws OAuthSystemException {

        try {
            URI location = new URI(request.getLocationUri());
            HttpRequestBase req;
            InputStream responseBody = new ByteArrayInputStream(new byte[0]);

            if (!OAuthUtils.isEmpty(requestMethod) && OAuth.HttpMethod.POST.equals(requestMethod)) {
                req = new HttpPost(location);
                HttpEntity entity = new StringEntity(request.getBody());
                ((HttpPost) req).setEntity(entity);
            } else {
                req = new HttpGet(location);
            }
            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    req.setHeader(header.getKey(), header.getValue());
                }
            }
            if (request.getHeaders() != null) {
                for (Map.Entry<String, String> header : request.getHeaders().entrySet()) {
                    req.setHeader(header.getKey(), header.getValue());
                }
            }
            HttpResponse response = client.execute(req);
            Header contentTypeHeader = null;

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseBody = entity.getContent();
                contentTypeHeader = entity.getContentType();
            }
            String contentType = null;
            if (contentTypeHeader != null) {
                contentType = contentTypeHeader.toString();
            }

            return OAuthClientResponseFactory
                    .createCustomResponse(responseBody, contentType, response.getStatusLine().getStatusCode(), getHeaders(response.getAllHeaders()),
                            responseClass);
        } catch (Exception e) {
            throw new OAuthSystemException(e);
        }
    }

    protected Map<String, List<String>> getHeaders(Header[] headers) {
        Map<String, List<String>> headersMap = new CaseInsensitiveMap<>();

        for (Header header : headers) {
            String headerKey = header.getName();
            String headerValue = header.getValue();
            if (headersMap.containsKey(headerKey)) {
                final List<String> headerValues = headersMap.get(headerKey);
                headerValues.add(headerValue);
            } else {
                List<String> headerValues = new ArrayList<>();
                headerValues.add(headerValue);
                headersMap.put(headerKey, headerValues);
            }
        }
        return headersMap;
    }
}
