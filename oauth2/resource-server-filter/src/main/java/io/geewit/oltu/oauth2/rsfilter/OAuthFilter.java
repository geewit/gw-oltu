package io.geewit.oltu.oauth2.rsfilter;

import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.error.OAuthError;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;
import io.geewit.oltu.oauth2.common.message.OAuthResponse;
import io.geewit.oltu.oauth2.common.message.types.ParameterStyle;
import io.geewit.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import io.geewit.oltu.oauth2.rs.response.OAuthRSResponse;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 *
 */
public class OAuthFilter implements Filter {

    public static final String OAUTH_RS_PROVIDER_CLASS = "oauth.rs.provider-class";

    public static final String RS_REALM = "oauth.rs.realm";
    public static final String RS_REALM_DEFAULT = "OAuth Protected Service";

    public static final String RS_TOKENS = "oauth.rs.tokens";
    public static final ParameterStyle RS_TOKENS_DEFAULT = ParameterStyle.HEADER;

    private static final String TOKEN_DELIMITER = ",";

    private String realm;

    private OAuthRSProvider provider;

    private ParameterStyle[] parameterStyles;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        provider = OAuthUtils
                .initiateServletContext(filterConfig, OAUTH_RS_PROVIDER_CLASS,
                        OAuthRSProvider.class);
        realm = filterConfig.getInitParameter(RS_REALM);
        if (OAuthUtils.isEmpty(realm)) {
            realm = RS_REALM_DEFAULT;
        }

        String parameterStylesString = filterConfig.getServletContext().getInitParameter(RS_TOKENS);
        if (OAuthUtils.isEmpty(parameterStylesString)) {
            parameterStyles = new ParameterStyle[]{RS_TOKENS_DEFAULT};
        } else {
            String[] parameters = parameterStylesString.split(TOKEN_DELIMITER);
            if (parameters.length > 0) {
                parameterStyles = new ParameterStyle[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    ParameterStyle tempParameterStyle = ParameterStyle.valueOf(parameters[i]);
                    parameterStyles[i] = tempParameterStyle;
                }
            }
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        try {

            // Make an OAuth Request out of this servlet request
            OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(req,
                    parameterStyles);

            // Get the access token
            String accessToken = oauthRequest.getAccessToken();

            final OAuthDecision decision = provider.validateRequest(realm, accessToken, req);

            final Principal principal = decision.getPrincipal();

            request = new HttpServletRequestWrapper((HttpServletRequest) request) {
                @Override
                public String getRemoteUser() {
                    return principal != null ? principal.getName() : null;
                }

                @Override
                public Principal getUserPrincipal() {
                    return principal;
                }

            };

            request.setAttribute(OAuth.OAUTH_CLIENT_ID, decision.getOAuthClient().getClientId());

            chain.doFilter(request, response);

        } catch (OAuthSystemException e) {
            throw new ServletException(e);
        } catch (OAuthProblemException e) {
            respondWithError(res, e);
        }

    }


    @Override
    public void destroy() {

    }

    private void respondWithError(HttpServletResponse resp, OAuthProblemException error)
            throws IOException, ServletException {

        OAuthResponse oauthResponse;

        try {
            if (OAuthUtils.isEmpty(error.getError())) {
                oauthResponse = OAuthRSResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .setRealm(realm)
                        .buildHeaderMessage();

            } else {

                int responseCode = HttpServletResponse.SC_UNAUTHORIZED;
                if (error.getError().equals(OAuthError.CodeResponse.INVALID_REQUEST)) {
                    responseCode = HttpServletResponse.SC_BAD_REQUEST;
                } else if (error.getError().equals(OAuthError.ResourceResponse.INSUFFICIENT_SCOPE)) {
                    responseCode = HttpServletResponse.SC_FORBIDDEN;
                }

                oauthResponse = OAuthRSResponse
                        .errorResponse(responseCode)
                        .setRealm(realm)
                        .setError(error.getError())
                        .setErrorDescription(error.getDescription())
                        .setErrorUri(error.getUri())
                        .buildHeaderMessage();
            }
            resp.addHeader(OAuth.HeaderType.WWW_AUTHENTICATE,
                    oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
            resp.sendError(oauthResponse.getResponseStatus());
        } catch (OAuthSystemException e) {
            throw new ServletException(e);
        }
    }
}
