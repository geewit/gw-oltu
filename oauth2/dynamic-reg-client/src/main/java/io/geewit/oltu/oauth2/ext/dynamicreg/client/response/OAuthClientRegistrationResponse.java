package io.geewit.oltu.oauth2.ext.dynamicreg.client.response;

import io.geewit.oltu.oauth2.client.response.OAuthClientResponse;
import io.geewit.oltu.oauth2.common.error.OAuthError;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.utils.JSONUtils;
import io.geewit.oltu.oauth2.ext.dynamicreg.client.validators.RegistrationValidator;
import io.geewit.oltu.oauth2.ext.dynamicreg.common.OAuthRegistration;

import java.util.List;
import java.util.Map;


/**
 *
 *
 *
 */
public class OAuthClientRegistrationResponse extends OAuthClientResponse {

    public OAuthClientRegistrationResponse() {
        validator = new RegistrationValidator();
    }

    @Override
    protected void init(String body, String contentType, int responseCode) throws OAuthProblemException {
        super.init(body, contentType, responseCode);
    }

    @Override
    protected void init(String body, String contentType, int responseCode, Map<String, List<String>> headers) throws OAuthProblemException {
        super.init(body, contentType, responseCode, headers);
    }

    protected void setBody(String body) throws OAuthProblemException {
        try {
            this.body = body;
            parameters = JSONUtils.parseJSON(body);
        } catch (Throwable e) {
            throw OAuthProblemException.error(OAuthError.CodeResponse.UNSUPPORTED_RESPONSE_TYPE,
                    "Invalid response! Response body is not application/json encoded");
        }
    }

    protected void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    protected void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getClientId() {
        return getParam(OAuthRegistration.Response.CLIENT_ID);
    }

    public String getClientSecret() {
        return getParam(OAuthRegistration.Response.CLIENT_SECRET);
    }

    public String getIssuedAt() {
        return getParam(OAuthRegistration.Response.ISSUED_AT);
    }

    public Long getExpiresIn() {
        String value = getParam(OAuthRegistration.Response.EXPIRES_IN);
        return value == null ? null : Long.valueOf(value);
    }

}
