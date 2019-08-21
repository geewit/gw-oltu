package io.geewit.oltu.oauth2.ext.dynamicreg.server.request;

import io.geewit.oltu.oauth2.as.request.OAuthRequest;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;
import io.geewit.oltu.oauth2.common.validators.OAuthValidator;
import io.geewit.oltu.oauth2.ext.dynamicreg.common.OAuthRegistration;
import io.geewit.oltu.oauth2.ext.dynamicreg.server.validator.PushPullValidator;


/**
 *
 */
public class OAuthServerRegistrationRequest extends OAuthRequest {

    private String type;

    private boolean isDiscovered;

    public OAuthServerRegistrationRequest(JSONHttpServletRequestWrapper request)
            throws OAuthSystemException, OAuthProblemException {
        this(request, false);
    }

    public OAuthServerRegistrationRequest(JSONHttpServletRequestWrapper request, boolean discover)
            throws OAuthSystemException, OAuthProblemException {
        super(request);
        if (discover) {
            discover();
        }
    }

    @Override
    protected OAuthValidator initValidator() {
        return new PushPullValidator();
    }

    public void discover() {
        if (OAuthRegistration.Type.PULL.equals(type)) {
            // discover            
        }
        isDiscovered = true;
    }

    public String getType() {
        return getParam(OAuthRegistration.Request.TYPE);
    }

    public String getClientName() {
        return getParam(OAuthRegistration.Request.CLIENT_NAME);
    }

    public String getClientUrl() {
        return getParam(OAuthRegistration.Request.CLIENT_URL);
    }

    public String getClientDescription() {
        return getParam(OAuthRegistration.Request.CLIENT_DESCRIPTION);
    }

    public String getClientIcon() {
        return getParam(OAuthRegistration.Request.CLIENT_ICON);
    }

    public String getRedirectURI() {
        return getParam(OAuthRegistration.Request.REDIRECT_URL);
    }

    public boolean isDiscovered() {
        return isDiscovered;
    }

}
