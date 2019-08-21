package io.geewit.oltu.oidc.client.response;

import io.geewit.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.jwt.JWT;
import io.geewit.oltu.oauth2.jwt.io.JWTReader;
import io.geewit.oltu.oidc.OpenIdConnect;

import java.util.Objects;

/**
 *
 */
public class OpenIdConnectResponse extends OAuthJSONAccessTokenResponse {

    private JWT idToken;

    @Override
    protected void init(String body, String contentType, int responseCode)
            throws OAuthProblemException {
        super.init(body, contentType, responseCode);
        try {
            idToken = new JWTReader().read(getParam(OpenIdConnect.ID_TOKEN));
        } catch (IllegalArgumentException ie) {
            //is not open id connect compliant
            //nothing to do.
        }

    }

    public final JWT getIdToken() {
        return idToken;
    }

    /**
     * ID Token Validation as per OpenID Connect
     * Basic Client Profile 1.0 draft 22 Section 2.4
     *
     * @param issuer
     * @param audience
     * @return
     */
    public boolean checkId(String issuer, String audience) {
        return Objects.equals(idToken.getClaimsSet().getIssuer(), issuer)
                && Objects.equals(idToken.getClaimsSet().getAudience(), audience)
                && idToken.getClaimsSet().getExpirationTime() < System
                .currentTimeMillis();
    }

}
