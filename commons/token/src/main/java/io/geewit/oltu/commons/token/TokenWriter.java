package io.geewit.oltu.commons.token;

public abstract class TokenWriter<T> {

    public final String write(T token) {
        if (token == null) {
            throw new IllegalArgumentException("Impossible to build a Token from a null JWS representation.");
        }

        String header = writeHeader(token);
        String encodedHeader = TokenDecoder.base64Encode(header);

        String body = writeBody(token);
        String encodedBody = TokenDecoder.base64Encode(body);

        String signature = writeSignature(token);

        return encodedHeader + '.' + encodedBody + '.' + signature;
    }

    protected abstract String writeHeader(T token);

    protected abstract String writeBody(T token);

    protected abstract String writeSignature(T token);

}
