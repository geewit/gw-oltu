package io.geewit.oltu.oauth2.common.parameters;

import io.geewit.oltu.oauth2.common.message.OAuthMessage;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 *
 */
public class BodyURLEncodedParametersApplier implements OAuthParametersApplier {

    public OAuthMessage applyOAuthParameters(OAuthMessage message, Map<String, Object> params) {

        String body = OAuthUtils.format(params.entrySet(), StandardCharsets.UTF_8.name());
        message.setBody(body);
        return message;

    }
}
