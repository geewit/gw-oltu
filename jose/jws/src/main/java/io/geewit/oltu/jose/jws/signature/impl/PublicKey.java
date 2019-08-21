package io.geewit.oltu.jose.jws.signature.impl;

import io.geewit.oltu.jose.jws.signature.VerifyingKey;

public class PublicKey implements VerifyingKey {

    java.security.PublicKey publicKey;

    public PublicKey(java.security.PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public java.security.PublicKey getPublicKey() {
        return publicKey;
    }

}