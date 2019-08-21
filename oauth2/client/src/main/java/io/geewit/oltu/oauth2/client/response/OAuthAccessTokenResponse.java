package io.geewit.oltu.oauth2.client.response;

import io.geewit.oltu.oauth2.client.validator.TokenValidator;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.token.OAuthToken;

import java.util.List;
import java.util.Map;

/**
 *
 *
 *
 */
public abstract class OAuthAccessTokenResponse extends OAuthClientResponse {

    public OAuthAccessTokenResponse() {
        validator = new TokenValidator();
    }

    public abstract String getAccessToken();

    public abstract String getTokenType();

    public abstract Long getExpiresIn();

    public abstract String getRefreshToken();

    public abstract String getScope();

    public abstract OAuthToken getOAuthToken();

    public String getBody() {
        return body;
    }

    @Override
    protected void init(String body, String contentType, int responseCode) throws OAuthProblemException {
        super.init(body, contentType, responseCode);
    }

    @Override
    protected void init(String body, String contentType, int responseCode, Map<String, List<String>> headers) throws OAuthProblemException {
        super.init(body, contentType, responseCode, headers);
    }
}
