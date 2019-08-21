package io.geewit.oltu.oauth2.common.exception;

/**
 *
 */
public class OAuthRuntimeException extends RuntimeException {
    public OAuthRuntimeException() {
        super();
    }

    public OAuthRuntimeException(String message) {
        super(message);
    }

    public OAuthRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public OAuthRuntimeException(Throwable cause) {
        super(cause);
    }
}
