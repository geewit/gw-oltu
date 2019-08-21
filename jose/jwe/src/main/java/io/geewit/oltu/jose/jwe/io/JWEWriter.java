package io.geewit.oltu.jose.jwe.io;

import io.geewit.oltu.commons.token.TokenDecoder;
import io.geewit.oltu.jose.jwe.JWE;

public final class JWEWriter {

    public final String write(JWE token) {
        if (token == null) {
            throw new IllegalArgumentException("Impossible to build a Token from a null JWS representation.");
        }

        String header = writeHeader(token);
        String encodedHeader = TokenDecoder.base64Encode(header);
        String encryptedKey = writeEncryptedKey(token);
        String contentEncryption = writeContentEncryption(token);

        return encodedHeader + '.' + encryptedKey + '.' + contentEncryption;
    }

    protected String writeHeader(JWE token) {
        return new JWEHeaderWriter().write(token.getHeader());
    }

    protected String writeEncryptedKey(JWE token) {
        return token.getEncryptedKey();
    }

    protected String writeContentEncryption(JWE token) {
        return token.getContentEncryption();
    }
}
