package io.geewit.oltu.oauth2.common.error;

/**
 *
 */
public interface OAuthError {

    //error response params
    String OAUTH_ERROR = "error";
    String OAUTH_ERROR_DESCRIPTION = "error_description";
    String OAUTH_ERROR_URI = "error_uri";

    interface CodeResponse {
        /**
         * The request is missing a required parameter, includes an
         unsupported parameter value, or is otherwise malformed.
         */
        String INVALID_REQUEST = "invalid_request";

        /**
         * The client is not authorized to request an authorization
         code using this method.
         */
        String UNAUTHORIZED_CLIENT = "unauthorized_client";

        /**
         * The resource owner or authorization server denied the
         request.
         */
        String ACCESS_DENIED = "access_denied";

        /**
         * The authorization server does not support obtaining an
         authorization code using this method.
         */
        String UNSUPPORTED_RESPONSE_TYPE = "unsupported_response_type";

        /**
         * The requested scope is invalid, unknown, or malformed.
         */
        String INVALID_SCOPE = "invalid_scope";

        /**
         * The authorization server encountered an unexpected
         condition which prevented it from fulfilling the request.
         */
        String SERVER_ERROR = "server_error";

        /**
         *         The authorization server is currently unable to handle
         the request due to a temporary overloading or maintenance
         of the server.
         */
        String TEMPORARILY_UNAVAILABLE = "temporarily_unavailable";

    }

    interface TokenResponse {
        /**
         The request is missing a required parameter, includes an
         unsupported parameter value, repeats a parameter,
         includes multiple credentials, utilizes more than one
         mechanism for authenticating the client, or is otherwise
         malformed.
         */
        String INVALID_REQUEST = "invalid_request";
        /**
         Client authentication failed (e.g. unknown client, no
         client authentication included, or unsupported
         authentication method).  The authorization server MAY
         return an HTTP 401 (Unauthorized) status code to indicate
         which HTTP authentication schemes are supported.  If the
         client attempted to authenticate via the "Authorization"
         request header field, the authorization server MUST
         respond with an HTTP 401 (Unauthorized) status code, and
         include the "WWW-Authenticate" response header field
         matching the authentication scheme used by the client.
         */
        String INVALID_CLIENT = "invalid_client";

        /**
         The provided authorization grant (e.g. authorization
         code, resource owner credentials, client credentials) is
         invalid, expired, revoked, does not match the redirection
         URI used in the authorization request, or was issued to
         another client.
         */
        String INVALID_GRANT = "invalid_grant";

        /**
         The authenticated client is not authorized to use this
         authorization grant type.
         */
        String UNAUTHORIZED_CLIENT = "unauthorized_client";

        /**
         The authorization grant type is not supported by the
         authorization server.
         */
        String UNSUPPORTED_GRANT_TYPE = "unsupported_grant_type";

        /**
         * The requested scope is invalid, unknown, malformed, or exceeds the scope granted by the resource owner.
         */
        String INVALID_SCOPE = "invalid_scope";
    }

    interface ResourceResponse {
        /**
         The request is missing a required parameter, includes an
         unsupported parameter value, repeats a parameter,
         includes multiple credentials, utilizes more than one
         mechanism for authenticating the client, or is otherwise
         malformed.
         */
        String INVALID_REQUEST = "invalid_request";


        String EXPIRED_TOKEN = "expired_token";

        /**
         * The request requires higher privileges than provided by the
         * access token.
         */
        String INSUFFICIENT_SCOPE = "insufficient_scope";

        /**
         * The access token provided is expired, revoked, malformed, or
         * invalid for other reasons.
         */
        String INVALID_TOKEN = "invalid_token";
    }

}
