package io.geewit.oltu.jose.jwe.encryption;


/**
 * Common definition of OAuth content encryption method algorithm.
 *
 * @param <EK> the {@link EncryptingKey} type.
 * @param <DK> the {@link DecryptingKey} type.
 */
public interface ContentEncryptMethod<EK extends EncryptingKey, DK extends DecryptingKey> extends EncryptMethod<EncryptingKey, DecryptingKey> {

    String encrypt(String header, String payload, byte[] encryptingKey);

    String decrypt(String header, String contentEncryption, byte[] decryptingKey);

    //TODO add validation??
}
