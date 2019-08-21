package io.geewit.oltu.oauth2.client.request;

import io.geewit.oltu.oauth2.common.OAuth;

public class OAuthBearerClientRequest extends OAuthClientRequest.OAuthRequestBuilder {

    public OAuthBearerClientRequest(String url) {
        super(url);
    }

    public OAuthBearerClientRequest setAccessToken(String accessToken) {
        this.parameters.put(OAuth.OAUTH_BEARER_TOKEN, accessToken);
        return this;
    }

}
