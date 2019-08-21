package io.geewit.oltu.commons.token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class TokenReader<T> {

    /**
     * The Base64 JSON string default separator.
     */
    private final Pattern base64urlTokenPattern = Pattern.compile("([a-zA-Z0-9-​_=]+)\\.([a-zA-Z0-9-_​=]+)\\.([a-zA-Z0-9-_=]+)");

    /**
     * Read the base64url token string
     *
     * @param base64String
     * @return
     */
    public T read(String base64String) {
        if (base64String == null || base64String.isEmpty()) {
            throw new IllegalArgumentException("Impossible to obtain a Token from a null or empty string");
        }

        // TODO improve multi-line tokens
        StringBuilder buffer = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new StringReader(base64String))) {
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line.trim());
            }
        } catch (IOException ignored) {
            // it cannot happen
        }
        // swallow it

        Matcher matcher = base64urlTokenPattern.matcher(buffer.toString());
        if (!matcher.matches()) {
            throw new IllegalArgumentException(base64String
                    + "is not a valid Token, it does not match with the pattern: "
                    + base64urlTokenPattern.pattern());
        }

        // HEADER
        String header = matcher.group(1);
        String decodedHeader = TokenDecoder.base64Decode(header);

        // BODY
        String body = matcher.group(2);
        String decodedBody = TokenDecoder.base64Decode(body);

        // SIGNATURE
        // Keep signature encoded in base64url
        String signature = matcher.group(3);

        return build(base64String, decodedHeader, decodedBody, signature);
    }

    /**
     * Build the token reader
     *
     * @param rawString
     * @param decodedHeader
     * @param decodedBody
     * @param encodedSignature
     * @return
     */
    protected abstract T build(String rawString, String decodedHeader, String decodedBody, String encodedSignature);
}
