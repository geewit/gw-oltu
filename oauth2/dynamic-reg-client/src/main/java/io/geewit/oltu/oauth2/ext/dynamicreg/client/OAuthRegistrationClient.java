package io.geewit.oltu.oauth2.ext.dynamicreg.client;

import io.geewit.oltu.oauth2.client.HttpClient;
import io.geewit.oltu.oauth2.client.OAuthClient;
import io.geewit.oltu.oauth2.client.request.OAuthClientRequest;
import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;
import io.geewit.oltu.oauth2.ext.dynamicreg.client.response.OAuthClientRegistrationResponse;

import java.util.HashMap;
import java.util.Map;


/**
 *
 */
public class OAuthRegistrationClient extends OAuthClient {

    public OAuthRegistrationClient(HttpClient oauthClient) {
        super(oauthClient);
    }

    public OAuthClientRegistrationResponse clientInfo(
            OAuthClientRequest request)
            throws OAuthSystemException, OAuthProblemException {
        String method = OAuth.HttpMethod.POST;
        Map<String, String> headers = new HashMap<>();
        headers.put(OAuth.HeaderType.CONTENT_TYPE, OAuth.ContentType.JSON);

        return httpClient.execute(request, headers, method, OAuthClientRegistrationResponse.class);
    }
}
