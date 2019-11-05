package io.geewit.oltu.oauth2.rs.extractor;

import io.geewit.oltu.oauth2.common.OAuth;

import javax.servlet.http.HttpServletRequest;


/**
 *
 */
public class BearerBodyTokenExtractor implements TokenExtractor {

    @Override
    public String getAccessToken(HttpServletRequest request) {
        String token = request.getParameter(OAuth.OAUTH_BEARER_TOKEN);
        if (token == null) {
            token = request.getParameter(OAuth.OAUTH_TOKEN);
        }
        return token;
    }

    @Override
    public String getAccessToken(HttpServletRequest request, String tokenName) {
        return request.getParameter(tokenName);
    }
}
