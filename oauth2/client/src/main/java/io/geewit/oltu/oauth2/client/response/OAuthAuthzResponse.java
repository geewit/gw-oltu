package io.geewit.oltu.oauth2.client.response;

import io.geewit.oltu.oauth2.client.validator.CodeTokenValidator;
import io.geewit.oltu.oauth2.client.validator.CodeValidator;
import io.geewit.oltu.oauth2.client.validator.OAuthClientValidator;
import io.geewit.oltu.oauth2.client.validator.TokenValidator;
import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 *
 *
 *
 */
public class OAuthAuthzResponse extends OAuthClientResponse {

    private HttpServletRequest request;

    protected OAuthAuthzResponse(HttpServletRequest request, OAuthClientValidator validator) {
        this.request = request;
        Map<String, String[]> params = request.getParameterMap();
        params.forEach((key, values) -> {
            if (!OAuthUtils.hasEmptyValues(values)) {
                parameters.put(key, values[0]);
            }
        });
        this.validator = validator;
    }

    public static OAuthAuthzResponse oauthCodeAuthzResponse(HttpServletRequest request)
            throws OAuthProblemException {
        OAuthAuthzResponse response = new OAuthAuthzResponse(request, new CodeValidator());
        response.validate();
        return response;
    }

    public static OAuthAuthzResponse oAuthCodeAndTokenAuthzResponse(HttpServletRequest request)
            throws OAuthProblemException {
        OAuthAuthzResponse response = new OAuthAuthzResponse(request, new CodeTokenValidator());
        response.validate();
        return response;
    }

    public static OAuthAuthzResponse oauthTokenAuthzResponse(HttpServletRequest request)
            throws OAuthProblemException {
        OAuthAuthzResponse response = new OAuthAuthzResponse(request, new TokenValidator());
        response.validate();
        return response;
    }

    public String getAccessToken() {
        return getParam(OAuth.OAUTH_ACCESS_TOKEN);
    }

    public Long getExpiresIn() {
        String value = getParam(OAuth.OAUTH_EXPIRES_IN);
        return value == null ? null : Long.valueOf(value);
    }

    public String getScope() {
        return getParam(OAuth.OAUTH_SCOPE);
    }

    public String getCode() {
        return getParam(OAuth.OAUTH_CODE);
    }

    public String getState() {
        return getParam(OAuth.OAUTH_STATE);
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    protected void setBody(String body) {
        this.body = body;
    }

    protected void setContentType(String contentType) {
        this.contentType = contentType;
    }

    protected void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

}
