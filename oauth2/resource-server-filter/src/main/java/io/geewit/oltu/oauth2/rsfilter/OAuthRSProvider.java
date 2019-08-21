package io.geewit.oltu.oauth2.rsfilter;

import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;

import javax.servlet.http.HttpServletRequest;


/**
 *
 */
public interface OAuthRSProvider {

    OAuthDecision validateRequest(String rsId, String token, HttpServletRequest req) throws
            OAuthProblemException;

}