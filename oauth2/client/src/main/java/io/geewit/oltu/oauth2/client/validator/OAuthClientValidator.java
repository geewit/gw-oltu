package io.geewit.oltu.oauth2.client.validator;

import io.geewit.oltu.oauth2.client.response.OAuthClientResponse;
import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.error.OAuthError;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;

import java.util.*;

/**
 *
 *
 *
 */
public abstract class OAuthClientValidator {

    protected Map<String, String[]> requiredParams = new HashMap<>();
    protected List<String> notAllowedParams = new ArrayList<>();

    public void validate(OAuthClientResponse response) throws OAuthProblemException {
        validateErrorResponse(response);
        validateParameters(response);
    }

    public void validateParameters(OAuthClientResponse response) throws OAuthProblemException {
        validateRequiredParameters(response);
        validateNotAllowedParameters(response);
    }

    public void validateErrorResponse(OAuthClientResponse response) throws OAuthProblemException {
        String error = response.getParam(OAuthError.OAUTH_ERROR);
        if (!OAuthUtils.isEmpty(error)) {
            String errorDesc = response.getParam(OAuthError.OAUTH_ERROR_DESCRIPTION);
            String errorUri = response.getParam(OAuthError.OAUTH_ERROR_URI);
            String state = response.getParam(OAuth.OAUTH_STATE);
            throw OAuthProblemException.error(error).description(errorDesc).uri(errorUri).responseStatus(response.getResponseCode()).state(state);
        }
    }


    public void validateRequiredParameters(OAuthClientResponse response) throws OAuthProblemException {
        Set<String> missingParameters = new HashSet<>();

        for (Map.Entry<String, String[]> requiredParam : requiredParams.entrySet()) {
            String paramName = requiredParam.getKey();
            String val = response.getParam(paramName);
            if (OAuthUtils.isEmpty(val)) {
                missingParameters.add(paramName);
            } else {
                String[] dependentParams = requiredParam.getValue();
                if (!OAuthUtils.hasEmptyValues(dependentParams)) {
                    for (String dependentParam : dependentParams) {
                        val = response.getParam(dependentParam);
                        if (OAuthUtils.isEmpty(val)) {
                            missingParameters.add(dependentParam);
                        }
                    }
                }
            }
        }

        if (!missingParameters.isEmpty()) {
            throw OAuthUtils.handleMissingParameters(missingParameters);
        }
    }

    public void validateNotAllowedParameters(OAuthClientResponse response) throws OAuthProblemException {
        List<String> notAllowedParameters = new ArrayList<>();
        for (String requiredParam : notAllowedParams) {
            String val = response.getParam(requiredParam);
            if (!OAuthUtils.isEmpty(val)) {
                notAllowedParameters.add(requiredParam);
            }
        }
        if (!notAllowedParameters.isEmpty()) {
            throw OAuthUtils.handleNotAllowedParametersOAuthException(notAllowedParameters);
        }
    }


}
