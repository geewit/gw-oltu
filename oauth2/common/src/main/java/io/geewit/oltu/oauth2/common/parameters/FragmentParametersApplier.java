package io.geewit.oltu.oauth2.common.parameters;

import io.geewit.oltu.oauth2.common.message.OAuthMessage;
import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class FragmentParametersApplier implements OAuthParametersApplier {

    public OAuthMessage applyOAuthParameters(OAuthMessage message, Map<String, Object> params) {

        String messageUrl = message.getLocationUri();
        if (messageUrl != null) {
            StringBuilder url = new StringBuilder(messageUrl);

            params.remove(OAuth.OAUTH_REFRESH_TOKEN);

            String fragmentQuery = OAuthUtils.format(params.entrySet(), StandardCharsets.UTF_8.name());

            if (!OAuthUtils.isEmpty(fragmentQuery)) {
                if (params.size() > 0) {
                    url.append("#").append(fragmentQuery);
                }
            }
            message.setLocationUri(url.toString());
        }
        return message;
    }
}
