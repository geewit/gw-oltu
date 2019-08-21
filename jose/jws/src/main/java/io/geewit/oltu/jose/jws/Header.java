package io.geewit.oltu.jose.jws;

import io.geewit.oltu.commons.json.CustomizableEntity;

import java.util.Map;

/**
 * Represents the Header as defined in the section 4 of the JWS specification.
 */
public final class Header extends CustomizableEntity {

    /**
     * The {@code alg} JWS Header parameter.
     */
    private final String algorithm;

    /**
     * The {@code jku} JWS Header parameter.
     */
    private final String jwkSetUrl;

    /**
     * The {@code jwk} JWS Header parameter.
     */
    private final String jsonWebKey;

    /**
     * The {@code x5u} JWS Header parameter.
     */
    private final String x509url;

    /**
     * The {@code x5t} JWS Header parameter.
     */
    private final String x509CertificateThumbprint;

    /**
     * The {@code x5c} JWS Header parameter.
     */
    private final String x509CertificateChain;

    /**
     * The {@code kid} JWS Header parameter.
     */
    private final String keyId;

    /**
     * The {@code typ} JWS Header parameter.
     */
    private final String type;

    /**
     * The {@code cty} JWS Header parameter.
     */
    private final String contentType;

    /**
     * The {@code crit} JWS Header parameter.
     */
    private final String[] critical;

    Header(String algorithm,
           String jwkSetUrl,
           String jsonWebKey,
           String x509url,
           String x509CertificateThumbprint,
           String x509CertificateChain,
           String keyId,
           String type,
           String contentType,
           String[] critical,
           Map<String, Object> customFields) {
        super(customFields);
        this.algorithm = algorithm;
        this.jwkSetUrl = jwkSetUrl;
        this.jsonWebKey = jsonWebKey;
        this.x509url = x509url;
        this.x509CertificateThumbprint = x509CertificateThumbprint;
        this.x509CertificateChain = x509CertificateChain;
        this.keyId = keyId;
        this.type = type;
        this.contentType = contentType;
        this.critical = critical;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getJwkSetUrl() {
        return jwkSetUrl;
    }

    public String getJsonWebKey() {
        return jsonWebKey;
    }

    public String getX509url() {
        return x509url;
    }

    public String getX509CertificateThumbprint() {
        return x509CertificateThumbprint;
    }

    public String getX509CertificateChain() {
        return x509CertificateChain;
    }

    public String getKeyId() {
        return keyId;
    }

    public String getType() {
        return type;
    }

    public String getContentType() {
        return contentType;
    }

    public String[] getCritical() {
        return critical;
    }

}
