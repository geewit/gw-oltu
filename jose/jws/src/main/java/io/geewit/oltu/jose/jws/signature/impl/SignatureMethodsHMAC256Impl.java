package io.geewit.oltu.jose.jws.signature.impl;

import io.geewit.oltu.commons.token.TokenDecoder;
import io.geewit.oltu.jose.jws.JWSConstants;
import io.geewit.oltu.jose.jws.signature.SignatureMethod;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SignatureMethodsHMAC256Impl implements SignatureMethod<SymmetricKeyImpl, SymmetricKeyImpl> {

    @Override
    public String calculate(String header, String payload, SymmetricKeyImpl signingKey) {
        String stringToSign = header + "." + payload;
        byte[] bytes = stringToSign.getBytes();

        try {
            Mac mac = Mac.getInstance("HMACSHA256");
            mac.init(new SecretKeySpec(signingKey.getKey(), mac.getAlgorithm()));
            mac.update(bytes);
            bytes = mac.doFinal();

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        return TokenDecoder.base64Encode(bytes);
    }

    @Override
    public boolean verify(String signature, String header, String payload, SymmetricKeyImpl verifyingKey) {
        String signed = calculate(header, payload, verifyingKey);
        return signed.equals(signature);
    }

    @Override
    public String getAlgorithm() {
        return JWSConstants.HS256;
    }

}
