package io.geewit.oltu.oauth2.common.domain.credentials;

/**
 *
 */
public interface Credentials {

    String getClientId();

    String getClientSecret();

    Long getIssuedAt();

    Long getExpiresIn();
}
