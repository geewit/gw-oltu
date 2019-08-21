package io.geewit.oltu.jose.jws.io;

import io.geewit.oltu.commons.token.TokenWriter;
import io.geewit.oltu.jose.jws.JWS;

public final class JWSWriter extends TokenWriter<JWS> {

    @Override
    protected String writeHeader(JWS token) {
        return new JWSHeaderWriter().write(token.getHeader());
    }

    @Override
    protected String writeBody(JWS token) {
        return token.getPayload();
    }

    @Override
    protected String writeSignature(JWS token) {
        return token.getSignature();
    }

}
