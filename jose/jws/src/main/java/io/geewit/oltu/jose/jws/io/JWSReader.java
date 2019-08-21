package io.geewit.oltu.jose.jws.io;

import io.geewit.oltu.commons.token.TokenReader;
import io.geewit.oltu.jose.jws.JWS;

/**
 * A {@link JWS} reader.
 * <p>
 * TODO understand if JWT can be reused to avoid code duplication!
 */
public final class JWSReader extends TokenReader<JWS> {

    @Override
    protected JWS build(String rawString, String decodedHeader, String decodedBody, String encodedSignature) {
        final JWS.Builder jwsBuilder = new JWS.Builder(rawString);

        new JWSHeaderParser(jwsBuilder).read(decodedHeader);

        return jwsBuilder
                .setPayload(decodedBody)
                .setSignature(encodedSignature)
                .build();
    }

}
