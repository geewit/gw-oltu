package io.geewit.oltu.oauth2.jwt.io;

import io.geewit.oltu.commons.json.CustomizableEntityWriter;
import io.geewit.oltu.oauth2.jwt.ClaimsSet;

import java.util.List;

public final class JWTClaimsSetWriter extends CustomizableEntityWriter<ClaimsSet> implements JWTConstants {

    @Override
    protected void handleProperties(ClaimsSet claimsSet) {
        List<String> audiences = claimsSet.getAudiences();
        set(AUDIENCE, audiences.size() > 1 ? audiences : claimsSet.getAudience());
        set(ISSUER, claimsSet.getIssuer());
        set(JWT_ID, claimsSet.getJwdId());
        set(NOT_BEFORE, claimsSet.getNotBefore());
        set(SUBJECT, claimsSet.getSubject());
        set(TYPE, claimsSet.getType());
        set(EXPIRATION_TIME, claimsSet.getExpirationTime());
        set(ISSUED_AT, claimsSet.getIssuedAt());
    }

}
