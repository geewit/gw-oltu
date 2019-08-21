package io.geewit.oltu.oauth2.client.response;

import io.geewit.oltu.oauth2.client.validator.OAuthClientValidator;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 *
 */
public abstract class OAuthClientResponse {

    protected String body;
    protected String contentType;
    protected int responseCode;
    protected Map<String, List<String>> headers;

    protected OAuthClientValidator validator;
    protected Map<String, Object> parameters = new HashMap<>();

    public String getParam(String param) {
        Object value = parameters.get(param);
        return value == null ? null : String.valueOf(value);
    }

    public int getResponseCode() {
        return responseCode;
    }

    protected abstract void setResponseCode(int responseCode);

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    /**
     * Allows setting the response body to a String value.
     *
     * @param body A String representing the response body.
     * @throws OAuthProblemException exception
     * @throws UnsupportedOperationException for subclasses that only
     *         support InputStream as body
     */
    protected void setBody(String body) throws OAuthProblemException {
        throw new UnsupportedOperationException();
    }

    /**
     * Allows setting the response body to an InputStream value.
     *
     * @param body An InputStream representing the response body.
     * @throws OAuthProblemException exception
     * @throws UnsupportedOperationException for subclasses that only
     *         support String as body
     */
    protected void setBody(InputStream body) throws OAuthProblemException {
        throw new UnsupportedOperationException();
    }

    protected abstract void setContentType(String contentType);

    protected void init(String body, String contentType, int responseCode, Map<String, List<String>> headers)
            throws OAuthProblemException {
        this.setBody(body);
        this.setContentType(contentType);
        this.setResponseCode(responseCode);
        this.setHeaders(headers);
        this.validate();
    }

    protected void init(String body, String contentType, int responseCode)
            throws OAuthProblemException {
        init(body, contentType, responseCode, new HashMap<>());
    }

    /**
     * Default implementation that converts the body InputStream to a String and delegates
     * to {@link #init(String, String, int)}.
     * This implementation ensures backwards compatibility, as many subclasses expect String
     * type bodies. At the same time it can be overridden to also deal with binary InputStreams.
     *
     * @param body an InputStream representing the response body
     * @param contentType the content type of the response.
     * @param responseCode the HTTP response code of the response.
     * @param headers The HTTP response headers
     * @throws OAuthProblemException exception
     */
    protected void init(InputStream body, String contentType, int responseCode, Map<String, List<String>> headers)
            throws OAuthProblemException {
        try {
            init(OAuthUtils.saveStreamAsString(body), contentType, responseCode);
        } catch (final IOException e) {
            throw OAuthProblemException.error(e.getMessage());
        }
    }

    protected void validate() throws OAuthProblemException {
        validator.validate(this);
    }

}
