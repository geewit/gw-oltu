package io.geewit.oltu.oauth2.rs.extractor;

import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;

import javax.servlet.http.HttpServletRequest;


/**
 *
 */
public class BearerHeaderTokenExtractor implements TokenExtractor {


    @Override
    public String getAccessToken(HttpServletRequest request) {
        String authzHeader = request.getHeader(OAuth.HeaderType.AUTHORIZATION);
        return OAuthUtils.getAuthHeaderField(authzHeader);
    }

    @Override
    public String getAccessToken(HttpServletRequest request, String tokenName) {
        String authzHeader = request.getHeader(OAuth.HeaderType.AUTHORIZATION);
        return OAuthUtils.getAuthHeaderField(authzHeader);
    }


}
