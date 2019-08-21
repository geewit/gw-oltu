package io.geewit.oltu.oauth2.common.validators;

import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;

import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 *
 */
public interface OAuthValidator<T extends HttpServletRequest> {

    void validateMethod(T request) throws OAuthProblemException;

    void validateContentType(T request) throws OAuthProblemException;

    void validateRequiredParameters(T request) throws OAuthProblemException;

    void validateOptionalParameters(T request) throws OAuthProblemException;

    void validateNotAllowedParameters(T request) throws OAuthProblemException;

    void validateClientAuthenticationCredentials(T request) throws OAuthProblemException;

    void performAllValidations(T request) throws OAuthProblemException;

}
