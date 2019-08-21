package io.geewit.oltu.jose.jwe.encryption;

import io.geewit.oltu.jose.jwe.ContentEncryptionKey;

/**
 * Common definition of OAuth key encryption method algorithm.
 *
 * @param <EK> the {@link EncryptingKey} type.
 * @param <DK> the {@link DecryptingKey} type.
 */
public interface KeyEncryptMethod<EK extends EncryptingKey, DK extends DecryptingKey> extends EncryptMethod<EncryptingKey, DecryptingKey> {

    //TODO change to wrap?
    ContentEncryptionKey encrypt(byte[] cek, EK encryptingKey);

    ContentEncryptionKey encrypt(EK encryptingKey);

    byte[] decrypt(String encryptedKey, DK decryptingKey);

    byte[] decrypt(String encryptedKey);

    //TODO add validation??

}
