package io.geewit.oltu.oauth2.as.validator;


import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.validators.AbstractValidator;

/**
 *
 *
 *
 */
public class AssertionValidator extends AbstractValidator {

    public AssertionValidator() {
        requiredParams.add(OAuth.OAUTH_GRANT_TYPE);
        requiredParams.add(OAuth.OAUTH_ASSERTION_TYPE);
        requiredParams.add(OAuth.OAUTH_ASSERTION);
    }


}
