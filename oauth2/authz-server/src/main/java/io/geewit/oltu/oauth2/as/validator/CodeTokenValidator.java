package io.geewit.oltu.oauth2.as.validator;

import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.error.OAuthError;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.validators.AbstractValidator;

import javax.servlet.http.HttpServletRequest;


/**
 *
 *
 *
 */
public class CodeTokenValidator extends AbstractValidator<HttpServletRequest> {

    public CodeTokenValidator() {
        requiredParams.add(OAuth.OAUTH_RESPONSE_TYPE);
        requiredParams.add(OAuth.OAUTH_CLIENT_ID);
        requiredParams.add(OAuth.OAUTH_REDIRECT_URI);
    }

    @Override
    public void validateMethod(HttpServletRequest request) throws OAuthProblemException {
        String method = request.getMethod();
        if (!method.equals(OAuth.HttpMethod.GET) && !method.equals(OAuth.HttpMethod.POST)) {
            throw OAuthProblemException.error(OAuthError.CodeResponse.INVALID_REQUEST)
                    .description("Method not correct.");
        }
    }

    @Override
    public void validateContentType(HttpServletRequest request) {
    }
}

