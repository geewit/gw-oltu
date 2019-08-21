package io.geewit.oltu.oauth2.ext.dynamicreg.server.response;

import io.geewit.oltu.oauth2.as.response.OAuthASResponse;
import io.geewit.oltu.oauth2.ext.dynamicreg.common.OAuthRegistration;

/**
 *
 */
public class OAuthServerRegistrationResponse extends OAuthASResponse {

    protected OAuthServerRegistrationResponse(String url, int responseStatus) {
        super(url, responseStatus);
    }

    public static OAuthRegistrationResponseBuilder status(int code) {
        return new OAuthRegistrationResponseBuilder(code);
    }

    public static class OAuthRegistrationResponseBuilder extends OAuthResponseBuilder {

        public OAuthRegistrationResponseBuilder(int responseCode) {
            super(responseCode);
        }

        public OAuthRegistrationResponseBuilder setClientId(String value) {
            this.parameters.put(OAuthRegistration.Response.CLIENT_ID, value);
            return this;
        }

        public OAuthRegistrationResponseBuilder setClientSecret(String value) {
            this.parameters.put(OAuthRegistration.Response.CLIENT_SECRET, value);
            return this;
        }

        public OAuthRegistrationResponseBuilder setIssuedAt(String value) {
            this.parameters.put(OAuthRegistration.Response.ISSUED_AT, value);
            return this;
        }

        public OAuthRegistrationResponseBuilder setExpiresIn(String value) {
            this.parameters.put(OAuthRegistration.Response.EXPIRES_IN, Long.parseLong(value));
            return this;
        }

        public OAuthRegistrationResponseBuilder setExpiresIn(Long value) {
            this.parameters.put(OAuthRegistration.Response.EXPIRES_IN, value);
            return this;
        }

        public OAuthRegistrationResponseBuilder setParam(String key, String value) {
            this.parameters.put(key, value);
            return this;
        }

        public OAuthRegistrationResponseBuilder location(String location) {
            this.location = location;
            return this;
        }
    }
}
