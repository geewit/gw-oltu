package io.geewit.oltu.oauth2.common.token;

/**
 * Interface declaring accessor methods for the basic fields of 
 * an access token response. 
 * <p> 
 * See:
 * <a href="http://tools.ietf.org/html/rfc6749#section-5.1">http://tools.ietf.org/html/rfc6749#section-5.1</a>
 */
public interface OAuthToken {

    String getAccessToken();

    String getTokenType();

    Long getExpiresIn();

    String getRefreshToken();

    String getScope();
}
