package io.geewit.oltu.oauth2.client.response;

import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.error.OAuthError;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.token.BasicOAuthToken;
import io.geewit.oltu.oauth2.common.token.OAuthToken;
import io.geewit.oltu.oauth2.common.utils.JSONUtils;

/**
 *
 */
public class OAuthJSONAccessTokenResponse extends OAuthAccessTokenResponse {

    public OAuthJSONAccessTokenResponse() {
        super();
    }

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

    public String getScope() {
        return getParam(OAuth.OAUTH_SCOPE);
    }

    public OAuthToken getOAuthToken() {
        return new BasicOAuthToken(getAccessToken(), getTokenType(), getExpiresIn(), getRefreshToken(), getScope());
    }

    public String getRefreshToken() {
        return getParam(OAuth.OAUTH_REFRESH_TOKEN);
    }

    protected void setBody(String body) throws OAuthProblemException {

        try {
            this.body = body;
            parameters = JSONUtils.parseJSON(body);
        } catch (Throwable e) {
            throw OAuthProblemException.error(OAuthError.CodeResponse.UNSUPPORTED_RESPONSE_TYPE,
                    "Invalid response! Response body is not " + OAuth.ContentType.JSON + " encoded");
        }
    }

    protected void setContentType(String contentType) {
        this.contentType = contentType;
    }


    protected void setResponseCode(int code) {
        this.responseCode = code;
    }

}
