package io.geewit.oltu.commons.token;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public interface TokenDecoder {

    /**
     * Empty Line separator for rfc 2045 section 6.8
     * {@link org.apache.commons.codec.binary.Base64}
     */
    byte[] LINE_SEPARATOR = {};

    static String base64Decode(String base64encoded) {
        return new String(new Base64(-1, LINE_SEPARATOR, true).decode(base64encoded), StandardCharsets.UTF_8);
    }

    static byte[] base64DecodeToByte(String base64encoded) {
        return new Base64(-1, LINE_SEPARATOR, true).decode(base64encoded);
    }

    static String base64Encode(String input) {
        return new String(new Base64(-1, LINE_SEPARATOR, true).encode(input.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

    static String base64Encode(byte[] input) {
        return new String(new Base64(-1, LINE_SEPARATOR, true).encode(input));
    }
}
