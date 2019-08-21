package io.geewit.oltu.oauth2.client;

import io.geewit.oltu.oauth2.client.request.OAuthClientRequest;
import io.geewit.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import io.geewit.oltu.oauth2.client.response.OAuthClientResponse;
import io.geewit.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;

import java.util.HashMap;
import java.util.Map;

/**
 * OAuth Client - exposes a high-level API for Client Applications
 *
 *
 *
 *
 */
public class OAuthClient {

    protected HttpClient httpClient;

    public OAuthClient(HttpClient oauthClient) {
        this.httpClient = oauthClient;
    }

    public <T extends OAuthAccessTokenResponse> T accessToken(
            OAuthClientRequest request,
            Class<T> responseClass)
            throws OAuthSystemException, OAuthProblemException {

        return accessToken(request, OAuth.HttpMethod.POST, responseClass);
    }

    public <T extends OAuthAccessTokenResponse> T accessToken(
            OAuthClientRequest request, String requestMethod, Class<T> responseClass)
            throws OAuthSystemException, OAuthProblemException {

        Map<String, String> headers = new HashMap<>();
        headers.put(OAuth.HeaderType.CONTENT_TYPE, OAuth.ContentType.URL_ENCODED);

        return httpClient.execute(request, headers, requestMethod, responseClass);
    }

    public OAuthJSONAccessTokenResponse accessToken(
            OAuthClientRequest request)
            throws OAuthSystemException, OAuthProblemException {
        return accessToken(request, OAuthJSONAccessTokenResponse.class);
    }

    public OAuthJSONAccessTokenResponse accessToken(
            OAuthClientRequest request, String requestMethod)
            throws OAuthSystemException, OAuthProblemException {
        return accessToken(request, requestMethod, OAuthJSONAccessTokenResponse.class);
    }

    public <T extends OAuthClientResponse> T resource(OAuthClientRequest request, String requestMethod, Class<T> responseClass) throws OAuthSystemException, OAuthProblemException {
        return httpClient.execute(request, null, requestMethod, responseClass);
    }

    public void shutdown() {
        httpClient.shutdown();
    }
}
