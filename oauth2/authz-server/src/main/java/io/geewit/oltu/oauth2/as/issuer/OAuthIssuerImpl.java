package io.geewit.oltu.oauth2.as.issuer;


import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;

/**
 *
 */
public class OAuthIssuerImpl implements OAuthIssuer {

    private ValueGenerator vg;

    public OAuthIssuerImpl(ValueGenerator vg) {
        this.vg = vg;
    }

    public String accessToken() throws OAuthSystemException {
        return vg.generateValue();
    }

    public String refreshToken() throws OAuthSystemException {
        return vg.generateValue();
    }

    public String authorizationCode() throws OAuthSystemException {
        return vg.generateValue();
    }
}
