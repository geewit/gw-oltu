package io.geewit.oltu.oauth2.jwt.io;

import io.geewit.oltu.commons.token.TokenReader;
import io.geewit.oltu.oauth2.jwt.JWT;

/**
 * A {@link JWT} reader.
 */
public final class JWTReader extends TokenReader<JWT> {

    @Override
    protected JWT build(String rawString, String decodedHeader, String decodedBody, String encodedSignature) {
        JWT.Builder jwtBuilder = new JWT.Builder(rawString);

        new JWTHeaderParser(jwtBuilder).read(decodedHeader);
        new JWTClaimsSetParser(jwtBuilder).read(decodedBody);

        return jwtBuilder.setSignature(encodedSignature).build();
    }

}
