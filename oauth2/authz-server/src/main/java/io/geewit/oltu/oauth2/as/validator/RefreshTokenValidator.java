package io.geewit.oltu.oauth2.as.validator;


import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.validators.AbstractValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * Validator that checks for the required fields in an OAuth Token request with the Refresh token grant type.
 * This validator enforces client authentication either through basic authentication or body parameters.
 *
 *
 */
public class RefreshTokenValidator extends AbstractValidator<HttpServletRequest> {

    public RefreshTokenValidator() {
        requiredParams.add(OAuth.OAUTH_GRANT_TYPE);
        requiredParams.add(OAuth.OAUTH_REFRESH_TOKEN);

        enforceClientAuthentication = true;
    }

}
