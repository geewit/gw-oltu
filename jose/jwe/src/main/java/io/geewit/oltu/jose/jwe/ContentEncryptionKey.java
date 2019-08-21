package io.geewit.oltu.jose.jwe;

public class ContentEncryptionKey {

    private final byte[] contentEncryptionKey;
    private final String encryptedKey;

    public ContentEncryptionKey(byte[] contentEncryptionKey, String encryptedKey) {
        super();
        this.contentEncryptionKey = contentEncryptionKey;
        this.encryptedKey = encryptedKey;
    }

    public byte[] getContentEncryptionKey() {
        return contentEncryptionKey;
    }

    public String getEncryptedKey() {
        return encryptedKey;
    }
}
