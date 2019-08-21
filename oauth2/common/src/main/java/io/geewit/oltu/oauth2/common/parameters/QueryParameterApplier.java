package io.geewit.oltu.oauth2.common.parameters;

import io.geewit.oltu.oauth2.common.message.OAuthMessage;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 *
 *
 *
 */
public class QueryParameterApplier implements OAuthParametersApplier {

    public OAuthMessage applyOAuthParameters(OAuthMessage message, Map<String, Object> params) {

        String messageUrl = message.getLocationUri();
        if (messageUrl != null) {
            boolean containsQuestionMark = messageUrl.contains("?");
            StringBuilder url = new StringBuilder(messageUrl);

            StringBuffer query = new StringBuffer(OAuthUtils.format(params.entrySet(), StandardCharsets.UTF_8.name()));

            if (!OAuthUtils.isEmpty(query.toString())) {
                if (containsQuestionMark) {
                    url.append("&").append(query);
                } else {
                    url.append("?").append(query);
                }
            }
            message.setLocationUri(url.toString());
        }
        return message;
    }
}
