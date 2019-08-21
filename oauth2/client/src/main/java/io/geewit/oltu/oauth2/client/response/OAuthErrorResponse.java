package io.geewit.oltu.oauth2.client.response;


import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;

/**
 *
 *
 *
 */
public class OAuthErrorResponse {

    private String error;
    private String errorDescription;
    private String errorUri;
    private String state;

    public OAuthErrorResponse(OAuthProblemException ex) {
        this.error = ex.getError();
        this.errorDescription = ex.getDescription();
        this.errorUri = ex.getUri();
        this.state = ex.getState();
    }

    public String getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public String getErrorUri() {
        return errorUri;
    }

    public String getState() {
        return state;
    }
}
