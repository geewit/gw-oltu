package io.geewit.oltu.oauth2.ext.dynamicreg.client.request;

import io.geewit.oltu.oauth2.client.request.OAuthClientRequest;
import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;
import io.geewit.oltu.oauth2.common.parameters.JSONBodyParametersApplier;
import io.geewit.oltu.oauth2.ext.dynamicreg.common.OAuthRegistration;


/**
 * OAuth Registration Request
 *
 */
public class OAuthClientRegistrationRequest extends OAuthClientRequest {

    protected OAuthClientRegistrationRequest(String url) {
        super(url);
    }

    public static OAuthRegistrationRequestBuilder location(String url, String type) {
        return new OAuthRegistrationRequestBuilder(url, type);
    }

    public static class OAuthRegistrationRequestBuilder extends OAuthRequestBuilder {

        public OAuthRegistrationRequestBuilder(String url, String type) {
            super(url);
            this.parameters.put(OAuthRegistration.Request.TYPE, type);
        }

        public OAuthRegistrationRequestBuilder setName(String value) {
            this.parameters.put(OAuthRegistration.Request.CLIENT_NAME, value);
            return this;
        }

        public OAuthRegistrationRequestBuilder setUrl(String value) {
            this.parameters.put(OAuthRegistration.Request.CLIENT_URL, value);
            return this;
        }

        public OAuthRegistrationRequestBuilder setDescription(String value) {
            this.parameters.put(OAuthRegistration.Request.CLIENT_DESCRIPTION, value);
            return this;
        }

        public OAuthRegistrationRequestBuilder setIcon(String value) {
            this.parameters.put(OAuthRegistration.Request.CLIENT_ICON, value);
            return this;
        }

        public OAuthRegistrationRequestBuilder setRedirectURL(String uri) {
            this.parameters.put(OAuthRegistration.Request.REDIRECT_URL, uri);
            return this;
        }

        public OAuthClientRequest buildJSONMessage() throws OAuthSystemException {
            OAuthClientRequest request = new OAuthClientRegistrationRequest(url);
            this.applier = new JSONBodyParametersApplier();
            return (OAuthClientRequest) applier.applyOAuthParameters(request, parameters);
        }

    }
}
