package io.geewit.oltu.jose.jwe.io;

interface JWEConstants {

    // header defined in the JWE specification
    // https://tools.ietf.org/html/rfc7516

    /**
     * The {@code alg} JWE Header key.
     */
    String ALGORITHM = "alg";

    /**
     * The {@code enc} JWE Header key.
     */
    String ENCRYPTION_ALGORITHM = "enc";

    /**
     * The {@code zip} JWE Header key.
     */
    String COMPRESSION = "zip";

    /**
     * The {@code jku} JWE Header key.
     */
    String JWK_SET_URL = "jku";

    /**
     * The {@code jwk} JWE Header key.
     */
    String JSON_WEB_KEY = "jwk";

    /**
     * The {@code kid} JWE Header key.
     */
    String KEY_ID = "kid";

    /**
     * The {@code x5u} JWE Header key.
     */
    String X509_URL = "x5u";

    /**
     * The {@code x5t} JWE Header key.
     */
    String X509_CERTIFICATE_THUMBPRINT = "x5t";

    /**
     * The {@code x5c} JWE Header key.
     */
    String X509_CERTIFICATE_CHAIN = "x5c";

    /**
     * The {@code typ} JWE Header key.
     */
    String TYPE = "typ";

    /**
     * The {@code cty} JWE Header key.
     */
    String CONTENT_TYPE = "cty";

    /**
     * The {@code crit} JWE Header key.
     */
    String CRITICAL = "crit";

}
