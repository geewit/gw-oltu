package io.geewit.oltu.oauth2.common.parameters;

import io.geewit.oltu.oauth2.common.message.OAuthMessage;
import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;

import java.util.Map;

/**
 *
 */
public class WWWAuthHeaderParametersApplier implements OAuthParametersApplier {

    public OAuthMessage applyOAuthParameters(OAuthMessage message, Map<String, Object> params) {
        String header = OAuthUtils.encodeOAuthHeader(params);
        message.addHeader(OAuth.HeaderType.WWW_AUTHENTICATE, header);
        return message;
    }
}
