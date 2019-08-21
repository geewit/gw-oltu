package io.geewit.oltu.oauth2.as.request;

import io.geewit.oltu.oauth2.as.validator.UnauthenticatedAuthorizationCodeValidator;
import io.geewit.oltu.oauth2.as.validator.UnauthenticatedPasswordValidator;
import io.geewit.oltu.oauth2.as.validator.UnauthenticatedRefreshTokenValidator;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;
import io.geewit.oltu.oauth2.common.message.types.GrantType;
import io.geewit.oltu.oauth2.common.validators.OAuthValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * The Unauthenticated OAuth Authorization Server class that validates whether a given HttpServletRequest is a valid
 * OAuth Token request.
 * This class accepts requests that are NOT authenticated, that is requests that do not contain a client_secret.
 * IMPORTANT: The ClientCredentials Grant Type is NOT supported by this class since client authentication is required
 * for this grant type. In order to support the client credentials grant type please use the {@link OAuthTokenRequest}
 * class.
 */
public class OAuthUnauthenticatedTokenRequest extends AbstractOAuthTokenRequest {

    public OAuthUnauthenticatedTokenRequest(HttpServletRequest request) throws OAuthSystemException,
            OAuthProblemException {
        super(request);
    }

    @Override
    protected OAuthValidator<HttpServletRequest> initValidator() throws OAuthProblemException, OAuthSystemException {
        validators.put(GrantType.PASSWORD.toString(), UnauthenticatedPasswordValidator.class);
        validators.put(GrantType.AUTHORIZATION_CODE.toString(), UnauthenticatedAuthorizationCodeValidator.class);
        validators.put(GrantType.REFRESH_TOKEN.toString(), UnauthenticatedRefreshTokenValidator.class);
        return super.initValidator();
    }
}
