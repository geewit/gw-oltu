package io.geewit.oltu.jose.jws.signature.impl;

import io.geewit.oltu.jose.jws.signature.SymmetricKey;

/**
 * Symmetric key implementation used for both <i>sign</i> and <i>verify</i>
 * operations.
 */
public class SymmetricKeyImpl implements SymmetricKey {

    private byte[] key;

    public SymmetricKeyImpl(byte[] key) {
        this.key = key;
    }

    public byte[] getKey() {
        return key;
    }

}
