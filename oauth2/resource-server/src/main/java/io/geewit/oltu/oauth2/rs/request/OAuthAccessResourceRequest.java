package io.geewit.oltu.oauth2.rs.request;


import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.error.OAuthError;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;
import io.geewit.oltu.oauth2.common.message.types.ParameterStyle;
import io.geewit.oltu.oauth2.common.message.types.TokenType;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;
import io.geewit.oltu.oauth2.common.validators.OAuthValidator;
import io.geewit.oltu.oauth2.rs.BearerResourceServer;
import io.geewit.oltu.oauth2.rs.ResourceServer;
import io.geewit.oltu.oauth2.rs.extractor.TokenExtractor;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class OAuthAccessResourceRequest {

    protected static Map<TokenType, Class<BearerResourceServer>> tokens = new HashMap<>();
    private HttpServletRequest request;
    private ParameterStyle[] parameterStyles;
    private TokenType[] tokenTypes;
    private ParameterStyle usedParameterStyle;
    private ResourceServer usedResourceServer;
    private TokenExtractor extractor;

    static {
        tokens.put(TokenType.BEARER, BearerResourceServer.class);
        //TODO add MACResourceServer - see AMBER-41
    }

    public OAuthAccessResourceRequest(HttpServletRequest request)
            throws OAuthSystemException, OAuthProblemException {
        this(request, new TokenType[]{OAuth.DEFAULT_TOKEN_TYPE}, new ParameterStyle[]{OAuth.DEFAULT_PARAMETER_STYLE});
    }

    public OAuthAccessResourceRequest(HttpServletRequest request, ParameterStyle... parameterStyles)
            throws OAuthSystemException, OAuthProblemException {
        this(request, new TokenType[]{OAuth.DEFAULT_TOKEN_TYPE}, parameterStyles);
    }

    public OAuthAccessResourceRequest(HttpServletRequest request, TokenType... tokenTypes)
            throws OAuthSystemException, OAuthProblemException {
        this(request, tokenTypes, new ParameterStyle[]{OAuth.DEFAULT_PARAMETER_STYLE});
    }

    public OAuthAccessResourceRequest(HttpServletRequest request, TokenType[] tokenTypes, ParameterStyle[] parameterStyles)
            throws OAuthSystemException, OAuthProblemException {
        this.request = request;
        this.tokenTypes = tokenTypes;
        this.parameterStyles = parameterStyles;
        this.validate();
    }

    public static ResourceServer instantiateResourceServer(TokenType tokenType) throws OAuthSystemException {
        Class<BearerResourceServer> clazz = tokens.get(tokenType);
        if (clazz == null) {
            throw new OAuthSystemException("Cannot instantiate a resource server.");
        }
        return OAuthUtils.instantiateClass(clazz);
    }

    public String getAccessToken() {
        return extractor.getAccessToken(request);
    }

    private void validate() throws OAuthSystemException, OAuthProblemException {

        int foundValidStyles = 0;
        boolean lackAuthInfo = false;
        OAuthProblemException ex = null;
        String lackAuthReason = "OAuth parameters were not found";
        for (TokenType tokenType : tokenTypes) {
            ResourceServer resourceServer = instantiateResourceServer(tokenType);
            for (ParameterStyle style : parameterStyles) {
                try {
                    OAuthValidator validator = resourceServer.instantiateValidator(style);
                    validator.validateContentType(request);
                    validator.validateMethod(request);
                    validator.validateRequiredParameters(request);

                    usedParameterStyle = style;
                    usedResourceServer = resourceServer;
                    foundValidStyles++;
                } catch (OAuthProblemException e) {
                    //request lacks any authentication information?
                    if (OAuthUtils.isEmpty(e.getError())) {
                        lackAuthInfo = true;
                        lackAuthReason = e.getDescription();
                    } else {
                        ex = OAuthProblemException.error(e.getError(), e.getDescription());
                    }
                }
            }
        }

        if (foundValidStyles > 1) {
            throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_REQUEST,
                    "Found more than one mechanism for authenticating client");
        }

        if (ex != null) {
            throw ex;
        }

        if (foundValidStyles == 0 && lackAuthInfo) {
            throw OAuthProblemException.error(null, lackAuthReason);
        }

        if (foundValidStyles == 0) {
            throw OAuthProblemException.error(OAuthError.TokenResponse.INVALID_REQUEST,
                    "OAuth parameters were not found");
        }

        extractor = usedResourceServer.instantiateExtractor(usedParameterStyle);
    }

}
