package io.geewit.oltu.oauth2.as.issuer;

import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;

/**
 *
 */
public interface OAuthIssuer {
    String accessToken() throws OAuthSystemException;

    String authorizationCode() throws OAuthSystemException;

    String refreshToken() throws OAuthSystemException;
}
