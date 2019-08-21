package io.geewit.oltu.oauth2.common;

import io.geewit.oltu.oauth2.common.message.types.ParameterStyle;
import io.geewit.oltu.oauth2.common.message.types.TokenType;

/**
 *
 *
 *
 */
public interface OAuth {

    String OAUTH_RESPONSE_TYPE = "response_type";
    String OAUTH_CLIENT_ID = "client_id";
    String OAUTH_CLIENT_SECRET = "client_secret";
    String OAUTH_REDIRECT_URI = "redirect_uri";
    String OAUTH_USERNAME = "username";
    String OAUTH_PASSWORD = "password";
    String OAUTH_ASSERTION_TYPE = "assertion_type";
    String OAUTH_ASSERTION = "assertion";
    String OAUTH_SCOPE = "scope";
    String OAUTH_STATE = "state";
    String OAUTH_GRANT_TYPE = "grant_type";
    String OAUTH_HEADER_NAME = "Bearer";
    //Authorization response params
    String OAUTH_CODE = "code";
    String OAUTH_ACCESS_TOKEN = "access_token";
    String OAUTH_EXPIRES_IN = "expires_in";
    String OAUTH_REFRESH_TOKEN = "refresh_token";
    String OAUTH_TOKEN_TYPE = "token_type";
    String OAUTH_TOKEN = "oauth_token";
    String OAUTH_TOKEN_DRAFT_0 = "access_token";
    String OAUTH_BEARER_TOKEN = "access_token";
    ParameterStyle DEFAULT_PARAMETER_STYLE = ParameterStyle.HEADER;
    TokenType DEFAULT_TOKEN_TYPE = TokenType.BEARER;
    String OAUTH_VERSION_DIFFER = "oauth_signature_method";
    String ASSERTION = "assertion";

    interface HttpMethod {
        String POST = "POST";
        String GET = "GET";
        String DELETE = "DELETE";
        String PUT = "PUT";
    }

    interface HeaderType {
        String CONTENT_TYPE = "Content-Type";
        String WWW_AUTHENTICATE = "WWW-Authenticate";
        String AUTHORIZATION = "Authorization";
    }

    interface WWWAuthHeader {
        String REALM = "realm";
    }

    interface ContentType {
        String URL_ENCODED = "application/x-www-form-urlencoded";
        String JSON = "application/json";
    }
}
