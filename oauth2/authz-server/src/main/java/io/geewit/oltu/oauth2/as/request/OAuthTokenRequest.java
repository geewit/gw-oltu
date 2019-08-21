package io.geewit.oltu.oauth2.as.request;

import io.geewit.oltu.oauth2.as.validator.AuthorizationCodeValidator;
import io.geewit.oltu.oauth2.as.validator.ClientCredentialValidator;
import io.geewit.oltu.oauth2.as.validator.PasswordValidator;
import io.geewit.oltu.oauth2.as.validator.RefreshTokenValidator;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;
import io.geewit.oltu.oauth2.common.message.types.GrantType;
import io.geewit.oltu.oauth2.common.validators.OAuthValidator;

import javax.servlet.http.HttpServletRequest;


/**
 * The Default OAuth Authorization Server class that validates whether a given HttpServletRequest is a valid
 * OAuth Token request.
 * IMPORTANT: This OAuthTokenRequest assumes that a token request requires client authentication.
 * Please see section 3.2.1 of the OAuth Specification: http://tools.ietf.org/html/rfc6749#section-3.2.1
 */
public class OAuthTokenRequest extends AbstractOAuthTokenRequest {

    /**
     * Create an OAuth Token request from a given HttpSerlvetRequest
     *
     * @param request the httpservletrequest that is validated and transformed into the OAuth Token Request
     * @throws OAuthSystemException  if an unexpected exception was thrown
     * @throws OAuthProblemException if the request was not a valid Token request this exception is thrown.
     */
    public OAuthTokenRequest(HttpServletRequest request) throws OAuthSystemException, OAuthProblemException {
        super(request);
    }

    @Override
    protected OAuthValidator<HttpServletRequest> initValidator() throws OAuthProblemException, OAuthSystemException {
        validators.put(GrantType.PASSWORD.toString(), PasswordValidator.class);
        validators.put(GrantType.CLIENT_CREDENTIALS.toString(), ClientCredentialValidator.class);
        validators.put(GrantType.AUTHORIZATION_CODE.toString(), AuthorizationCodeValidator.class);
        validators.put(GrantType.REFRESH_TOKEN.toString(), RefreshTokenValidator.class);
        return super.initValidator();
    }
}
