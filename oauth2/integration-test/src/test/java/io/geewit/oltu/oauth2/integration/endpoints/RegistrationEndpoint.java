package io.geewit.oltu.oauth2.integration.endpoints;

import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;
import io.geewit.oltu.oauth2.common.message.OAuthResponse;
import io.geewit.oltu.oauth2.ext.dynamicreg.server.request.JSONHttpServletRequestWrapper;
import io.geewit.oltu.oauth2.ext.dynamicreg.server.request.OAuthServerRegistrationRequest;
import io.geewit.oltu.oauth2.ext.dynamicreg.server.response.OAuthServerRegistrationResponse;
import io.geewit.oltu.oauth2.integration.CommonExt;

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
@Path("/register")
public class RegistrationEndpoint {


    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response register(@Context HttpServletRequest request) throws OAuthSystemException {


        OAuthServerRegistrationRequest oauthRequest;
        try {
            oauthRequest = new OAuthServerRegistrationRequest(new JSONHttpServletRequestWrapper(request));
            oauthRequest.discover();
            oauthRequest.getClientName();
            oauthRequest.getClientUrl();
            oauthRequest.getClientDescription();
            oauthRequest.getRedirectURI();

            OAuthResponse response = OAuthServerRegistrationResponse
                    .status(HttpServletResponse.SC_OK)
                    .setClientId(CommonExt.CLIENT_ID)
                    .setClientSecret(CommonExt.CLIENT_SECRET)
                    .setIssuedAt(CommonExt.ISSUED_AT)
                    .setExpiresIn(CommonExt.EXPIRES_IN)
                    .buildJSONMessage();
            return Response.status(response.getResponseStatus()).entity(response.getBody()).build();

        } catch (OAuthProblemException e) {
            OAuthResponse response = OAuthServerRegistrationResponse
                    .errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                    .error(e)
                    .buildJSONMessage();
            return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
        }

    }
}