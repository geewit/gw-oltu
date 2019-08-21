package io.geewit.oltu.oauth2.jwt.io;

import io.geewit.oltu.commons.token.TokenWriter;
import io.geewit.oltu.oauth2.jwt.JWT;

/**
 * A {@link JWT} writer.
 */
public final class JWTWriter extends TokenWriter<JWT> {

    @Override
    protected String writeHeader(JWT token) {
        return new JWTHeaderWriter().write(token.getHeader());
    }

    @Override
    protected String writeBody(JWT token) {
        return new JWTClaimsSetWriter().write(token.getClaimsSet());
    }

    @Override
    protected String writeSignature(JWT token) {
        return token.getSignature();
    }

}
