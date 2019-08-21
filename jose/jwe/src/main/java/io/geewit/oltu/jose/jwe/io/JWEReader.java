package io.geewit.oltu.jose.jwe.io;

import io.geewit.oltu.commons.token.TokenDecoder;
import io.geewit.oltu.jose.jwe.JWE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class JWEReader {
    /**
     * The Base64 JSON string default separator.
     */
    private final Pattern base64urlTokenPattern = Pattern.compile("([a-zA-Z0-9-_=]+)\\.([a-zA-Z0-9-_=]+)\\.([a-zA-Z0-9-_=]+)\\.([a-zA-Z0-9-_=]+)\\.([a-zA-Z0-9-_=]+)");

    /**
     * Read the base64url token string
     *
     * @param base64String
     * @return
     */
    public JWE read(String base64String) {
        if (base64String == null || base64String.isEmpty()) {
            throw new IllegalArgumentException("Impossible to obtain a Token from a null or empty string");
        }

        // TODO improve multi-line tokens
        String buffer = "";
        try (BufferedReader reader = new BufferedReader(new StringReader(base64String))) {
            buffer = reader.lines().map(String::trim).collect(Collectors.joining());
        } catch (IOException e) {
            // it cannot happen
        }
        // swallow it

        Matcher matcher = base64urlTokenPattern.matcher(buffer);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(base64String
                    + "is not a valid Token, it does not match with the pattern: "
                    + base64urlTokenPattern.pattern());
        }

        // HEADER
        String header = matcher.group(1);
        String decodedHeader = TokenDecoder.base64Decode(header);

        // ENCRYPTED KEY
        String encryptedKey = matcher.group(2);

        // IV
        String contentEncryption = matcher.group(3) + "." +
                // CIPHER TEXT
                matcher.group(4) + "." +
                // AUTHENTICATION TAG
                matcher.group(5);
        return build(decodedHeader, encryptedKey, contentEncryption);
    }

    protected JWE build(String decodedHeader, String encryptedKey, String contentEncryption) {
        final JWE.Builder jweBuilder = new JWE.Builder();

        new JWEHeaderParser(jweBuilder).read(decodedHeader);

        return jweBuilder
                .setEncryptedKey(encryptedKey)
                .setContentEncryption(contentEncryption)
                .build();
    }


}
