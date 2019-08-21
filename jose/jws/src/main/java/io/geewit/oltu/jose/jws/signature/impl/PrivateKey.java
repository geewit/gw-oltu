package io.geewit.oltu.jose.jws.signature.impl;

import io.geewit.oltu.jose.jws.signature.SigningKey;

public class PrivateKey implements SigningKey {

    java.security.PrivateKey privateKey;

    public PrivateKey(java.security.PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public java.security.PrivateKey getPrivateKey() {
        return privateKey;
    }

}