package io.geewit.oltu.jose.jwe.encryption;

/**
 * Common definition of OAuth encryption method algorithm.
 *
 * @param <EK> the {@link EncryptingKey} type.
 * @param <DK> the {@link DecryptingKey} type.
 */
public interface EncryptMethod<EK extends EncryptingKey, DK extends DecryptingKey> {

    String getAlgorithm();

}