package io.geewit.oltu.oauth2.jwt;

import io.geewit.oltu.commons.json.CustomizableEntity;

import java.util.Map;

import static java.lang.String.format;

/**
 * Represents the Header as defined in the 6.1 section of the JWT specification.
 *
 */
public final class Header extends CustomizableEntity {

    /**
     * The {@code typ} JWT Header parameter.
     */
    private final String type;

    /**
     * The {@code alg} JWT Header parameter.
     */
    private final String algorithm;

    /**
     * The {@code cty} JWT Header parameter.
     */
    private final String contentType;

    Header(String type,
           String algorithm,
           String contentType,
           Map<String, Object> customFields) {
        super(customFields);
        this.type = type;
        this.algorithm = algorithm;
        this.contentType = contentType;
    }

    /**
     * Returns the {@code typ} JWT Header parameter.
     *
     * @return the {@code typ} JWT Header parameter.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the {@code alg} JWT Header parameter.
     *
     * @return the {@code alg} JWT Header parameter.
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * Returns the {@code cty} JWT Header parameter.
     *
     * @return the {@code cty} JWT Header parameter.
     */
    public String getContentType() {
        return contentType;
    }

    @Override
    public String toString() {
        return format("{\"typ\": \"%s\", \"alg\": \"%s\", \"cty\": \"%s\" %s}", type, algorithm, contentType, super.toString());
    }

}
