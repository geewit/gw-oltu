package io.geewit.oltu.jose.jwe;

import io.geewit.oltu.commons.json.CustomizableEntity;

import java.util.Map;

/**
 * Represents the Header as defined in the section 4 of the JWE specification.
 *
 * @see  <a href="https://tools.ietf.org/html/rfc7516#section-4">rfc</a>
 */
public final class Header extends CustomizableEntity {

    /**
     * The {@code alg} JWE Header parameter.
     */
    private final String algorithm;

    /**
     * The {@code enc} JWE Header parameter.
     */
    private final String encryptionAlgorithm;

    /**
     * The {@code zip} JWE Header key.
     */
    private final String compressionAlgorithm;

    /**
     * The {@code jku} JWE Header parameter.
     */
    private final String jwkSetUrl;

    /**
     * The {@code jwk} JWE Header parameter.
     */
    private final String jsonWebKey;

    /**
     * The {@code x5u} JWE Header parameter.
     */
    private final String x509url;

    /**
     * The {@code x5t} JWE Header parameter.
     */
    private final String x509CertificateThumbprint;

    /**
     * The {@code x5c} JWE Header parameter.
     */
    private final String x509CertificateChain;

    /**
     * The {@code kid} JWE Header parameter.
     */
    private final String keyId;

    /**
     * The {@code typ} JWE Header parameter.
     */
    private final String type;

    /**
     * The {@code cty} JWE Header parameter.
     */
    private final String contentType;

    /**
     * The {@code crit} JWE Header parameter.
     */
    private final String[] critical;

    Header(String algorithm,
           String encryptionAlgorithm,
           String compressionAlgorithm,
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
        this.encryptionAlgorithm = encryptionAlgorithm;
        this.compressionAlgorithm = compressionAlgorithm;
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

    public String getEncryptionAlgorithm() {
        return encryptionAlgorithm;
    }

    public String getCompressionAlgorithm() {
        return compressionAlgorithm;
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
