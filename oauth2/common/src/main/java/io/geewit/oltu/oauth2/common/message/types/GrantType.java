package io.geewit.oltu.oauth2.common.message.types;

import io.geewit.core.utils.lang.enums.Name;

/**
 *
 */
public enum GrantType implements Name {
    // NONE("none"),
    AUTHORIZATION_CODE("authorization_code"),
    IMPLICIT("implicit"),
    PASSWORD("password"),
    REFRESH_TOKEN("refresh_token"),
    CLIENT_CREDENTIALS("client_credentials"),
    JWT_BEARER("urn:ietf:params:oauth:grant-type:jwt-bearer");

    private String name;

    GrantType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
