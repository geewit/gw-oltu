package io.geewit.oltu.jose.jwe.encryption;

/**
 * A key that marks can be used for both <i>sign</i> and <i>verify</i>
 * operations.
 */
public interface SymmetricKey extends EncryptingKey, DecryptingKey {

}