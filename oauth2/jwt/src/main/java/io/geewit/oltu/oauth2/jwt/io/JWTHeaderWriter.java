package io.geewit.oltu.oauth2.jwt.io;

import io.geewit.oltu.commons.json.CustomizableEntityWriter;
import io.geewit.oltu.oauth2.jwt.Header;

public final class JWTHeaderWriter extends CustomizableEntityWriter<Header> implements JWTConstants {

    @Override
    protected void handleProperties(Header header) {
        set(ALGORITHM, header.getAlgorithm());
        set(CONTENT_TYPE, header.getContentType());
        set(TYPE, header.getType());
    }

}
