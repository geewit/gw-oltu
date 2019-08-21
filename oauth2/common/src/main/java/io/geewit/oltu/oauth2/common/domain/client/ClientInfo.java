package io.geewit.oltu.oauth2.common.domain.client;

/**
 *
 */
public interface ClientInfo {

    String getClientId();

    String getClientSecret();

    Long getIssuedAt();

    Long getExpiresIn();

    String getRedirectUri();

    String getClientUri();

    String getDescription();

    String getName();

    String getIconUri();
}
