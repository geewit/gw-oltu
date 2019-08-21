package io.geewit.oltu.oauth2.ext.dynamicreg.common;

/**
 *
 */
public interface OAuthRegistration {
    interface Type {
        String PUSH = "push";
        String PULL = "pull";
    }

    interface Request {
        String TYPE = "type";
        String CLIENT_NAME = "client_name";
        String CLIENT_URL = "client_url";
        String CLIENT_DESCRIPTION = "client_description";
        String CLIENT_ICON = "client_icon";
        String REDIRECT_URL = "redirect_url";
    }

    interface Response {
        String CLIENT_ID = "client_id";
        String CLIENT_SECRET = "client_secret";
        String ISSUED_AT = "issued_at";
        String EXPIRES_IN = "expires_in";
    }
}
