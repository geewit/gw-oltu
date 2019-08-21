package io.geewit.oltu.oauth2.as.validator;

import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.validators.AbstractValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * Validator that checks for the required fields in an OAuth Token request with the Authorization Code grant type.
 * This validator enforces client authentication either through basic authentication or body parameters.
 */
public class AuthorizationCodeValidator extends AbstractValidator<HttpServletRequest> {

    public AuthorizationCodeValidator() {
        requiredParams.add(OAuth.OAUTH_GRANT_TYPE);
        requiredParams.add(OAuth.OAUTH_CODE);
        requiredParams.add(OAuth.OAUTH_REDIRECT_URI);

        enforceClientAuthentication = true;
    }

}
