package io.geewit.oltu.oauth2.client.request;

import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.message.OAuthMessage;
import io.geewit.oltu.oauth2.common.parameters.OAuthParametersApplier;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;

import java.util.Map;

/**
 *
 *
 *
 */
public class ClientHeaderParametersApplier implements OAuthParametersApplier {

    public OAuthMessage applyOAuthParameters(OAuthMessage message, Map<String, Object> params) {

        String header = OAuthUtils.encodeAuthorizationBearerHeader(params);
        message.addHeader(OAuth.HeaderType.AUTHORIZATION, header);
        return message;

    }

}
