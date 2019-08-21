package io.geewit.oltu.oauth2.client.response;

import io.geewit.oltu.oauth2.client.validator.ResourceValidator;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class OAuthResourceResponse extends OAuthClientResponse {

    private static final Logger LOG = LoggerFactory.getLogger(OAuthResourceResponse.class);

    private InputStream inputStream;

    private boolean bodyRetrieved = false;

    public OAuthResourceResponse() {
        this.validator = new ResourceValidator();
    }

    public String getBody() {
        if (bodyRetrieved && body == null) {
            throw new IllegalStateException("Cannot call getBody() after getBodyAsInputStream()");
        }

        if (body == null) {
            try {
                body = OAuthUtils.saveStreamAsString(getBodyAsInputStream());
                inputStream = null;
            } catch (IOException e) {
                LOG.error("Failed to convert InputStream to String", e);
            }
        }
        return body;
    }

    @Override
    protected void setBody(InputStream body) {
        this.inputStream = body;
    }

    @Override
    protected void setBody(String body) {
        this.body = body;
    }

    public int getResponseCode() {
        return responseCode;
    }

    @Override
    protected void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getContentType() {
        return contentType;
    }

    protected void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public InputStream getBodyAsInputStream() {
        if (bodyRetrieved && inputStream == null) {
            throw new IllegalStateException("Cannot call getBodyAsInputStream() after getBody()");
        }
        bodyRetrieved = true;
        return inputStream;
    }

    @Override
    protected void init(InputStream body, String contentType, int responseCode, Map<String, List<String>> headers) {
        this.setBody(body);
        this.setContentType(contentType);
        this.setResponseCode(responseCode);
        this.setHeaders(headers);
    }
}
