package io.geewit.oltu.oauth2.rs.extractor;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public interface TokenExtractor {

    String getAccessToken(HttpServletRequest request);

    String getAccessToken(HttpServletRequest request, String tokenName);

}
