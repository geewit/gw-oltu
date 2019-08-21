package io.geewit.oltu.oauth2.jwt.request;

import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.validators.AbstractValidator;

import javax.servlet.http.HttpServletRequest;

public class JWTBearerValidator extends AbstractValidator<HttpServletRequest> {

    public JWTBearerValidator() {
        requiredParams.add(OAuth.ASSERTION);
    }
}
