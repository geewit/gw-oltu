package io.geewit.oltu.jose.jws.signature;

/**
 * Common definition of OAuth signature method algorithm.
 *
 * @param <S> the {@link SigningKey} type.
 * @param <V> the {@link VerifyingKey} type.
 */
public interface SignatureMethod<S extends SigningKey, V extends VerifyingKey> {

    String calculate(String header, String payload, S signingKey);

    boolean verify(String signature, String header, String payload, V verifyingKey);

    String getAlgorithm();

}
