package io.geewit.oltu.jose.jws.io;

interface JWSConstants {

    // header defined in the JWT specification

    /**
     * The {@code alg} JWT Header key.
     */
    String ALGORITHM = "alg";

    /**
     * The {@code jku} JWT Header key.
     */
    String JWK_SET_URL = "jku";

    /**
     * The {@code jwk} JWT Header key.
     */
    String JSON_WEB_KEY = "jwk";

    /**
     * The {@code x5u} JWT Header key.
     */
    String X509_URL = "x5u";

    /**
     * The {@code x5t} JWT Header key.
     */
    String X509_CERTIFICATE_THUMBPRINT = "x5t";

    /**
     * The {@code x5c} JWT Header key.
     */
    String X509_CERTIFICATE_CHAIN = "x5c";

    /**
     * The {@code kid} JWT Header key.
     */
    String KEY_ID = "kid";

    /**
     * The {@code typ} JWT Header key.
     */
    String TYPE = "typ";

    /**
     * The {@code cty} JWT Header key.
     */
    String CONTENT_TYPE = "cty";

    /**
     * The {@code crit} JWT Header key.
     */
    String CRITICAL = "crit";

}
