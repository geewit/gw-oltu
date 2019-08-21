package io.geewit.oltu.oauth2.integration.endpoints;

import io.geewit.oltu.oauth2.as.issuer.MD5Generator;
import io.geewit.oltu.oauth2.as.issuer.OAuthIssuer;
import io.geewit.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import io.geewit.oltu.oauth2.as.request.OAuthUnauthenticatedTokenRequest;
import io.geewit.oltu.oauth2.as.response.OAuthASResponse;
import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.error.OAuthError;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;
import io.geewit.oltu.oauth2.common.message.OAuthResponse;
import io.geewit.oltu.oauth2.common.message.types.GrantType;
import io.geewit.oltu.oauth2.integration.Common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 *
 *
 *
 */
@Path("/unauth-token")
public class UnauthenticatedTokenEndpoint {

    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response token(@Context HttpServletRequest request) throws OAuthSystemException {

        OAuthUnauthenticatedTokenRequest oauthRequest;

        OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());

        try {
            oauthRequest = new OAuthUnauthenticatedTokenRequest(request);

            // check if clientid is valid
            if (!Common.CLIENT_ID.equals(oauthRequest.getParam(OAuth.OAUTH_CLIENT_ID))) {
                OAuthResponse response =
                        OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                                .setError(OAuthError.TokenResponse.INVALID_CLIENT).setErrorDescription("client_id not found")
                                .buildJSONMessage();
                return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
            }

            // do checking for different grant types
            if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE)
                    .equals(GrantType.AUTHORIZATION_CODE.toString())) {
                if (!Common.AUTHORIZATION_CODE.equals(oauthRequest.getParam(OAuth.OAUTH_CODE))) {
                    OAuthResponse response = OAuthASResponse
                            .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                            .setError(OAuthError.TokenResponse.INVALID_GRANT)
                            .setErrorDescription("invalid authorization code")
                            .buildJSONMessage();
                    return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
                }
            } else if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE)
                    .equals(GrantType.PASSWORD.toString())) {
                if (!Common.PASSWORD.equals(oauthRequest.getPassword())
                        || !Common.USERNAME.equals(oauthRequest.getUsername())) {
                    OAuthResponse response = OAuthASResponse
                            .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                            .setError(OAuthError.TokenResponse.INVALID_GRANT)
                            .setErrorDescription("invalid username or password")
                            .buildJSONMessage();
                    return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
                }
            } else if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE)
                    .equals(GrantType.REFRESH_TOKEN.toString())) {
                // refresh token is not supported in this implementation hence the oauth error.
                OAuthResponse response = OAuthASResponse
                        .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                        .setError(OAuthError.TokenResponse.INVALID_GRANT)
                        .setErrorDescription("invalid username or password")
                        .buildJSONMessage();
                return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
            }

            OAuthResponse response = OAuthASResponse
                    .tokenResponse(HttpServletResponse.SC_OK)
                    .setAccessToken(oauthIssuerImpl.accessToken())
                    .setExpiresIn("3600")
                    .buildJSONMessage();

            return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
        } catch (OAuthProblemException e) {
            OAuthResponse res = OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).error(e)
                    .buildJSONMessage();
            return Response.status(res.getResponseStatus()).entity(res.getBody()).build();
        }
    }
}