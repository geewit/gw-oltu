package io.geewit.oltu.oauth2.rs.extractor;

import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.rs.ResourceServer;

import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 *
 */
public class BearerQueryTokenExtractor implements TokenExtractor {

    @Override
    public String getAccessToken(HttpServletRequest request) {
        String token = ResourceServer.getQueryParameterValue(request, OAuth.OAUTH_BEARER_TOKEN);
        if (token == null) {
            token = ResourceServer.getQueryParameterValue(request, OAuth.OAUTH_TOKEN);
        }
        return token;
    }

    @Override
    public String getAccessToken(HttpServletRequest request, String tokenName) {
        return ResourceServer.getQueryParameterValue(request, tokenName);
    }

}
