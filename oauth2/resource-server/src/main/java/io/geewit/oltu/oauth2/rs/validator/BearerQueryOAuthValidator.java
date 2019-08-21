package io.geewit.oltu.oauth2.rs.validator;

import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.error.OAuthError;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;
import io.geewit.oltu.oauth2.common.validators.AbstractValidator;
import io.geewit.oltu.oauth2.rs.ResourceServer;

import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 *
 */
public class BearerQueryOAuthValidator extends AbstractValidator {

    @Override
    public void validateContentType(HttpServletRequest request) {
    }

    @Override
    public void validateMethod(HttpServletRequest request) {
    }

    @Override
    public void validateRequiredParameters(HttpServletRequest request) throws OAuthProblemException {

        String[] tokens = ResourceServer.getQueryParameterValues(request, OAuth.OAUTH_BEARER_TOKEN);
        if (OAuthUtils.hasEmptyValues(tokens)) {
            tokens = ResourceServer.getQueryParameterValues(request, OAuth.OAUTH_TOKEN);
            if (OAuthUtils.hasEmptyValues(tokens)) {
                throw OAuthProblemException.error(null, "Missing OAuth token.");
            }
        }

        if (tokens.length > 1) {
            throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_REQUEST, "Multiple tokens attached.");
        }

        String oauthVersionDiff = ResourceServer.getQueryParameterValue(request, OAuth.OAUTH_VERSION_DIFFER);
        if (!OAuthUtils.isEmpty(oauthVersionDiff)) {
            throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_REQUEST,
                    "Incorrect OAuth version. Found OAuth V1.0.");
        }
    }
}
