package io.geewit.oltu.oauth2.jwt.io;

import io.geewit.oltu.commons.json.CustomizableEntityReader;
import io.geewit.oltu.oauth2.jwt.JWT;

final class JWTHeaderParser extends CustomizableEntityReader<JWT, JWT.Builder> implements JWTConstants {

    public JWTHeaderParser(JWT.Builder builder) {
        super(builder);
    }

    @Override
    protected <T> boolean handleProperty(String key, T value) {
        if (ALGORITHM.equals(key)) {
            getBuilder().setHeaderAlgorithm(String.valueOf(value));
        } else if (TYPE.equals(key)) {
            getBuilder().setHeaderType(String.valueOf(value));
        } else if (CONTENT_TYPE.equals(key)) {
            getBuilder().setHeaderContentType(String.valueOf(value));
        } else {
            getBuilder().setHeaderCustomField(key, value);
        }

        return true;
    }

}
