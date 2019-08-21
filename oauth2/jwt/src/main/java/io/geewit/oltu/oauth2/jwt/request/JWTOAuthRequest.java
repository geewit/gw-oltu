package io.geewit.oltu.oauth2.jwt.request;

import io.geewit.oltu.oauth2.as.request.AbstractOAuthTokenRequest;
import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;
import io.geewit.oltu.oauth2.common.message.types.GrantType;
import io.geewit.oltu.oauth2.common.validators.OAuthValidator;

import javax.servlet.http.HttpServletRequest;

public class JWTOAuthRequest extends AbstractOAuthTokenRequest {

    /**
     * Create a JWT OAuth Token request from a given HttpSerlvetRequest
     *
     * @param request the httpservletrequest that is validated and transformed into the JWT OAuth Token Request
     * @throws OAuthSystemException  if an unexpected exception was thrown
     * @throws OAuthProblemException if the request was not a valid Token request this exception is thrown.
     */
    public JWTOAuthRequest(HttpServletRequest request) throws OAuthSystemException, OAuthProblemException {
        super(request);
    }

    @Override
    protected OAuthValidator<HttpServletRequest> initValidator()
            throws OAuthProblemException, OAuthSystemException {
        validators.put(GrantType.JWT_BEARER.toString(), JWTBearerValidator.class);
        return super.initValidator();
    }

    public String getAssertion() {
        return getParam(OAuth.ASSERTION);
    }
}
