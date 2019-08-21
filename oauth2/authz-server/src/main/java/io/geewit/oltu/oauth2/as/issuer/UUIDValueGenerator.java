package io.geewit.oltu.oauth2.as.issuer;


import io.geewit.utils.uuid.UUID;

/**
 * Exemplar OAuth Token Generator
 *
 */
public class UUIDValueGenerator implements ValueGenerator {

    @Override
    public String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }

    @Override
    public String generateValue(String param) {
        return UUID.fromString(UUID.nameUUIDFromBytes(param.getBytes()).toString()).toString();
    }
}
