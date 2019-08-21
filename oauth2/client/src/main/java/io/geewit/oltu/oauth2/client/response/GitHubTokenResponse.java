package io.geewit.oltu.oauth2.client.response;

import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.token.BasicOAuthToken;
import io.geewit.oltu.oauth2.common.token.OAuthToken;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;

/**
 * Custom response class
 */
public class GitHubTokenResponse extends OAuthAccessTokenResponse {

    @Override
    public String getAccessToken() {
        return getParam(OAuth.OAUTH_ACCESS_TOKEN);
    }

    @Override
    public String getTokenType() {
        return getParam(OAuth.OAUTH_TOKEN_TYPE);
    }

    @Override
    public Long getExpiresIn() {
        String value = getParam(OAuth.OAUTH_EXPIRES_IN);
        return value == null ? null : Long.valueOf(value);
    }

    @Override
    public String getRefreshToken() {
        return getParam(OAuth.OAUTH_REFRESH_TOKEN);
    }

    @Override
    public String getScope() {
        return getParam(OAuth.OAUTH_SCOPE);
    }

    @Override
    public OAuthToken getOAuthToken() {
        return new BasicOAuthToken(getAccessToken(), getTokenType(), getExpiresIn(), getRefreshToken(), getScope());
    }

    @Override
    protected void setBody(String body) {
        this.body = body;
        parameters = OAuthUtils.decodeForm(body);
    }

    @Override
    protected void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    protected void setResponseCode(int code) {
        this.responseCode = code;
    }
}
