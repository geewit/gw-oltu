package io.geewit.oltu.oauth2.as.validator;

import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.validators.AbstractValidator;

import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 *
 */
public class UnauthenticatedPasswordValidator extends AbstractValidator<HttpServletRequest> {

    public UnauthenticatedPasswordValidator() {
        requiredParams.add(OAuth.OAUTH_GRANT_TYPE);
        requiredParams.add(OAuth.OAUTH_CLIENT_ID);
        requiredParams.add(OAuth.OAUTH_USERNAME);
        requiredParams.add(OAuth.OAUTH_PASSWORD);

        enforceClientAuthentication = false;
    }

}
