package io.geewit.oltu.oauth2.as.request;

import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;
import io.geewit.oltu.oauth2.common.validators.OAuthValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The Abstract OAuth request for the Authorization server.
 */
public abstract class OAuthRequest {

    protected HttpServletRequest request;
    protected OAuthValidator<HttpServletRequest> validator;
    protected Map<String, Class<? extends OAuthValidator<HttpServletRequest>>> validators =
            new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(OAuthRequest.class);

    public OAuthRequest(HttpServletRequest request) throws OAuthSystemException, OAuthProblemException {
        this.request = request;
        validate();
    }

    public OAuthRequest() {
    }

    protected void validate() throws OAuthSystemException, OAuthProblemException {
        try {
            validator = initValidator();
            validator.validateMethod(request);
            validator.validateContentType(request);
            validator.validateRequiredParameters(request);
            validator.validateClientAuthenticationCredentials(request);
        } catch (OAuthProblemException e) {
            try {
                String redirectUri = request.getParameter(OAuth.OAUTH_REDIRECT_URI);
                if (!OAuthUtils.isEmpty(redirectUri)) {
                    e.setRedirectUri(redirectUri);
                }
            } catch (Exception ex) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Cannot read redirect_url from the request: {}", (Object) new String[]{ex.getMessage()});
                }
            }

            throw e;
        }

    }

    protected abstract OAuthValidator<HttpServletRequest> initValidator() throws OAuthProblemException,
            OAuthSystemException;

    public String getParam(String name) {
        return request.getParameter(name);
    }

    public String getClientId() {
        String[] creds = OAuthUtils.decodeClientAuthenticationHeader(request.getHeader(OAuth.HeaderType.AUTHORIZATION));
        if (creds != null) {
            return creds[0];
        }
        return getParam(OAuth.OAUTH_CLIENT_ID);
    }

    public String getRedirectURI() {
        return getParam(OAuth.OAUTH_REDIRECT_URI);
    }

    public String getClientSecret() {
        String[] creds = OAuthUtils.decodeClientAuthenticationHeader(request.getHeader(OAuth.HeaderType.AUTHORIZATION));
        if (creds != null) {
            return creds[1];
        }
        return getParam(OAuth.OAUTH_CLIENT_SECRET);
    }

    /**
     *
     * @return
     */
    public boolean isClientAuthHeaderUsed() {
        return OAuthUtils.decodeClientAuthenticationHeader(request.getHeader(OAuth.HeaderType.AUTHORIZATION)) != null;
    }

    public Set<String> getScopes() {
        String scopes = getParam(OAuth.OAUTH_SCOPE);
        return OAuthUtils.decodeScopes(scopes);
    }

}
