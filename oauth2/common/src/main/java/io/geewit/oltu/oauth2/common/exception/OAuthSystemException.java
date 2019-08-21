package io.geewit.oltu.oauth2.common.exception;

/**
 *
 */
public class OAuthSystemException extends Exception {

    public OAuthSystemException() {
        super();
    }

    public OAuthSystemException(String s) {
        super(s);
    }

    public OAuthSystemException(Throwable throwable) {
        super(throwable);
    }

    public OAuthSystemException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
