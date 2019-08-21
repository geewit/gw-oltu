package io.geewit.oltu.oauth2.rs.validator;

import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.error.OAuthError;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;
import io.geewit.oltu.oauth2.common.validators.AbstractValidator;

import javax.servlet.http.HttpServletRequest;


/**
 *
 *
 *
 */
public class BearerBodyOAuthValidator extends AbstractValidator {

    @Override
    public void validateMethod(HttpServletRequest request) throws OAuthProblemException {
        // Check if the method is POST, PUT, or DELETE
        String method = request.getMethod();
        if (!(OAuth.HttpMethod.POST.equals(method) || OAuth.HttpMethod.PUT.equals(method) || OAuth.HttpMethod
                .DELETE.equals(method))) {
            throw OAuthProblemException
                    .error(OAuthError.TokenResponse.INVALID_REQUEST)
                    .description("Incorrect method. POST, PUT, DELETE are supported.");
        }
    }

    @Override
    public void validateContentType(HttpServletRequest request) throws OAuthProblemException {
        if (OAuthUtils.isMultipart(request)) {
            throw OAuthProblemException.error(OAuthError.CodeResponse.INVALID_REQUEST).
                    description("Request is not single part.");
        }
        super.validateContentType(request);
    }


    @Override
    public void validateRequiredParameters(HttpServletRequest request) throws OAuthProblemException {

        if (OAuthUtils.isMultipart(request)) {
            throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_REQUEST).
                    description("Request is not single part.");
        }


        String[] tokens = request.getParameterValues(OAuth.OAUTH_BEARER_TOKEN);
        if (OAuthUtils.hasEmptyValues(tokens)) {
            tokens = request.getParameterValues(OAuth.OAUTH_TOKEN);
            if (OAuthUtils.hasEmptyValues(tokens)) {
                throw OAuthProblemException.error(null, "Missing OAuth token.");
            }
        }

        if (tokens.length > 1) {
            throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_REQUEST)
                    .description("Multiple tokens attached.");
        }

        String oauthVersionDiff = request.getParameter(OAuth.OAUTH_VERSION_DIFFER);
        if (!OAuthUtils.isEmpty(oauthVersionDiff)) {
            throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_REQUEST)
                    .description("Incorrect OAuth version. Found OAuth V1.0.");
        }

    }
}
