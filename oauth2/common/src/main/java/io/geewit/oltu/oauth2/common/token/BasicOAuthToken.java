package io.geewit.oltu.oauth2.common.token;

/**
 *
 */
public class BasicOAuthToken implements OAuthToken {
    protected String accessToken;
    protected String tokenType;
    protected Long expiresIn;
    protected String refreshToken;
    protected String scope;

    public BasicOAuthToken() {
    }

    public BasicOAuthToken(String accessToken, String tokenType, Long expiresIn, String refreshToken, String scope) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.scope = scope;
    }

    public BasicOAuthToken(String accessToken, String tokenType) {
        this(accessToken, tokenType, null, null, null);
    }

    public BasicOAuthToken(String accessToken, String tokenType, Long expiresIn) {
        this(accessToken, tokenType, expiresIn, null, null);
    }

    public BasicOAuthToken(String accessToken, String tokenType, Long expiresIn, String scope) {
        this(accessToken, tokenType, expiresIn, null, scope);
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String getTokenType() {
        return tokenType;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getScope() {
        return scope;
    }
}
