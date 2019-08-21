package io.geewit.oltu.oauth2.jwt.io;

import io.geewit.oltu.commons.json.CustomizableEntityReader;
import io.geewit.oltu.oauth2.jwt.JWT;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

final class JWTClaimsSetParser extends CustomizableEntityReader<JWT, JWT.Builder> implements JWTConstants {

    public JWTClaimsSetParser(JWT.Builder builder) {
        super(builder);
    }

    @Override
    protected <T> boolean handleProperty(String key, T value) {
        if (AUDIENCE.equals(key)) {
            handleAudience(value);
        } else if (EXPIRATION_TIME.equals(key)) {
            getBuilder().setClaimsSetExpirationTime(((Number) value).longValue());
        } else if (ISSUED_AT.equals(key)) {
            getBuilder().setClaimsSetIssuedAt(((Number) value).longValue());
        } else if (ISSUER.equals(key)) {
            getBuilder().setClaimsSetIssuer(String.valueOf(value));
        } else if (JWT_ID.equals(key)) {
            getBuilder().setClaimsSetJwdId(String.valueOf(value));
        } else if (NOT_BEFORE.equals(key)) {
            getBuilder().setClaimsSetNotBefore(String.valueOf(value));
        } else if (SUBJECT.equals(key)) {
            getBuilder().setClaimsSetSubject(String.valueOf(value));
        } else if (TYPE.equals(key)) {
            getBuilder().setClaimsSetType(String.valueOf(value));
        } else {
            getBuilder().setClaimsSetCustomField(key, value);
        }

        return true;
    }

    private <T> void handleAudience(T value) {
        if (value instanceof Collection) {
            getBuilder().setClaimsSetAudiences(collectionToStringList((Collection<?>) value));
        } else if (value instanceof Object[]) {
            getBuilder().setClaimsSetAudiences(arrayToStringList((Object[]) value));
        } else {
            getBuilder().setClaimsSetAudience(String.valueOf(value));
        }
    }

    private List<String> collectionToStringList(Collection<?> values) {
        return values.stream().map(String::valueOf).collect(Collectors.toList());
    }

    private List<String> arrayToStringList(Object[] values) {
        return Arrays.stream(values).map(String::valueOf).collect(Collectors.toList());
    }

}
