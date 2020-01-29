package io.geewit.oltu.oauth2.common.message.types;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.geewit.core.jackson.databind.serializer.EnumNameSerializer;
import io.geewit.core.utils.enums.Name;
import io.geewit.oltu.oauth2.common.jackson.deserializer.GrantTypeDeserializer;

/**
 *
 */
@JsonSerialize(using = EnumNameSerializer.class)
@JsonDeserialize(using = GrantTypeDeserializer.class)
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
