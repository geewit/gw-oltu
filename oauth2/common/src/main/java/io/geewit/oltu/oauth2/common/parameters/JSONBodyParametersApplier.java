package io.geewit.oltu.oauth2.common.parameters;

import io.geewit.oltu.oauth2.common.message.OAuthMessage;
import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;
import io.geewit.oltu.oauth2.common.utils.JSONUtils;

import java.util.Map;

/**
 *
 */
public class JSONBodyParametersApplier implements OAuthParametersApplier {

    public OAuthMessage applyOAuthParameters(OAuthMessage message, Map<String, Object> params)
 throws OAuthSystemException {
        String json;
        try {
            json = JSONUtils.buildJSON(params);
            message.setBody(json);
            return message;
        } catch (Throwable e) {
            throw new OAuthSystemException(e);
        }
    }

}
