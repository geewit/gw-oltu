package io.geewit.oltu.oauth2.common.validators;

import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *
 */
public abstract class AbstractValidator<T extends HttpServletRequest> implements OAuthValidator<T> {

    protected List<String> requiredParams = new ArrayList<>();
    protected Map<String, String[]> optionalParams = new HashMap<>();
    protected List<String> notAllowedParams = new ArrayList<>();
    protected boolean enforceClientAuthentication;

    @Override
    public void validateMethod(T request) throws OAuthProblemException {
        if (!request.getMethod().equals(OAuth.HttpMethod.POST)) {
            throw OAuthUtils.handleOAuthProblemException("Method not set to POST.");
        }
    }

    @Override
    public void validateContentType(T request) throws OAuthProblemException {
        String contentType = request.getContentType();
        final String expectedContentType = OAuth.ContentType.URL_ENCODED;
        if (!OAuthUtils.hasContentType(contentType, expectedContentType)) {
            throw OAuthUtils.handleBadContentTypeException(expectedContentType);
        }
    }

    @Override
    public void validateRequiredParameters(T request) throws OAuthProblemException {
        final Set<String> missingParameters = new HashSet<>();
        for (String requiredParam : requiredParams) {
            String val = request.getParameter(requiredParam);
            if (OAuthUtils.isEmpty(val)) {
                missingParameters.add(requiredParam);
            }
        }
        if (!missingParameters.isEmpty()) {
            throw OAuthUtils.handleMissingParameters(missingParameters);
        }
    }

    @Override
    public void validateOptionalParameters(T request) throws OAuthProblemException {
        final Set<String> missingParameters = new HashSet<>();

        for (Map.Entry<String, String[]> entry : optionalParams.entrySet()) {
            String paramName = entry.getKey();
            String[] dependentParams = entry.getValue();
            String val = request.getParameter(paramName);
            if (!OAuthUtils.isEmpty(val)) {
                if (!OAuthUtils.hasEmptyValues(dependentParams)) {
                    for (String dependentParam : dependentParams) {
                        val = request.getParameter(dependentParam);
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

    @Override
    public void validateNotAllowedParameters(T request) throws OAuthProblemException {
        List<String> notAllowedParameters = new ArrayList<>();
        for (String requiredParam : notAllowedParams) {
            String val = request.getParameter(requiredParam);
            if (!OAuthUtils.isEmpty(val)) {
                notAllowedParameters.add(requiredParam);
            }
        }
        if (!notAllowedParameters.isEmpty()) {
            throw OAuthUtils.handleNotAllowedParametersOAuthException(notAllowedParameters);
        }
    }

    @Override
    public void validateClientAuthenticationCredentials(T request) throws OAuthProblemException {
        if (enforceClientAuthentication) {
            Set<String> missingParameters = new HashSet<>();
            String clientAuthHeader = request.getHeader(OAuth.HeaderType.AUTHORIZATION);
            String[] clientCreds = OAuthUtils.decodeClientAuthenticationHeader(clientAuthHeader);

            // Only fallback to params if the auth header is not correct. Don't allow a mix of auth header vs params
            if (clientCreds == null || OAuthUtils.isEmpty(clientCreds[0]) || OAuthUtils.isEmpty(clientCreds[1])) {

                if (OAuthUtils.isEmpty(request.getParameter(OAuth.OAUTH_CLIENT_ID))) {
                    missingParameters.add(OAuth.OAUTH_CLIENT_ID);
                }
                if (OAuthUtils.isEmpty(request.getParameter(OAuth.OAUTH_CLIENT_SECRET))) {
                    missingParameters.add(OAuth.OAUTH_CLIENT_SECRET);
                }
            }

            if (!missingParameters.isEmpty()) {
                throw OAuthUtils.handleMissingParameters(missingParameters);
            }
        }
    }

    @Override
    public void performAllValidations(T request) throws OAuthProblemException {
        this.validateContentType(request);
        this.validateMethod(request);
        this.validateRequiredParameters(request);
        this.validateOptionalParameters(request);
        this.validateNotAllowedParameters(request);
        this.validateClientAuthenticationCredentials(request);
    }
}
