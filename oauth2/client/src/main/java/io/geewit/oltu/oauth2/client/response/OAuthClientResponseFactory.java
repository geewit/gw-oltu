package io.geewit.oltu.oauth2.client.response;

import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 *
 *
 *
 */
public class OAuthClientResponseFactory {

    public static OAuthClientResponse createGitHubTokenResponse(String body, String contentType,
                                                                int responseCode)
            throws OAuthProblemException {
        GitHubTokenResponse resp = new GitHubTokenResponse();
        resp.init(body, contentType, responseCode);
        return resp;
    }

    public static OAuthClientResponse createJSONTokenResponse(String body, String contentType,
                                                              int responseCode)
            throws OAuthProblemException {
        OAuthJSONAccessTokenResponse resp = new OAuthJSONAccessTokenResponse();
        resp.init(body, contentType, responseCode);
        return resp;
    }

    public static <T extends OAuthClientResponse> T createCustomResponse(String body, String contentType,
                                                                         int responseCode,
                                                                         Map<String, List<String>> headers,
                                                                         Class<T> clazz)
            throws OAuthSystemException, OAuthProblemException {

        OAuthClientResponse resp = OAuthUtils
                .instantiateClassWithParameters(clazz, null, null);

        resp.init(body, contentType, responseCode, headers);

        return (T) resp;
    }

    public static <T extends OAuthClientResponse> T createCustomResponse(InputStream body, String contentType,
                                                                         int responseCode,
                                                                         Map<String, List<String>> headers,
                                                                         Class<T> clazz)
            throws OAuthSystemException, OAuthProblemException {

        T resp = OAuthUtils.instantiateClassWithParameters(clazz, null, null);

        if (body == null) {
            body = new ByteArrayInputStream(new byte[0]);
        }
        resp.init(body, contentType, responseCode, headers);

        return resp;
    }
}
