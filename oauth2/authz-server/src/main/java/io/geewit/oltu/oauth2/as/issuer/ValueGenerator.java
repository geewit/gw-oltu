package io.geewit.oltu.oauth2.as.issuer;

import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;


/**
 *
 *
 *
 */
public interface ValueGenerator {
    String generateValue() throws OAuthSystemException;

    String generateValue(String param) throws OAuthSystemException;
}
