package io.geewit.oltu.oauth2.jwt.io;


interface JWTConstants {

    // header defined in the JWT specification

    /**
     * The {@code typ} JWT Header key.
     */
    String TYPE = "typ";

    /**
     * The {@code alg} JWT Header key.
     */
    String ALGORITHM = "alg";

    /**
     * The {@code cty} JWT Header key.
     */
    String CONTENT_TYPE = "cty";

    // reserved claims defined in the JWT specification

    /**
     * The {@code iss} JWT Claims Set key.
     */
    String ISSUER = "iss";

    /**
     * The {@code sub} JWT Claims Set key.
     */
    String SUBJECT = "sub";

    /**
     * The {@code aud} JWT Claims Set key.
     */
    String AUDIENCE = "aud";

    /**
     * The {@code exp} JWT Claims Set key.
     */
    String EXPIRATION_TIME = "exp";

    /**
     * The {@code nbf} JWT Claims Set key.
     */
    String NOT_BEFORE = "nbf";

    /**
     * The {@code iat} JWT Claims Set key.
     */
    String ISSUED_AT = "iat";

    /**
     * The {@code jti} JWT Claims Set key.
     */
    String JWT_ID = "jti";

}
