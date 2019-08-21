package io.geewit.oltu.jose.jws.signature.impl;

import io.geewit.oltu.commons.token.TokenDecoder;
import io.geewit.oltu.jose.jws.JWSConstants;
import io.geewit.oltu.jose.jws.signature.SignatureMethod;

import java.security.Signature;

/**
 * Class that asymmetrically sign and verify the issued token.
 */
public class SignatureMethodRSAImpl implements SignatureMethod<PrivateKey, PublicKey> {

    private String algorithm;

    public SignatureMethodRSAImpl(String algorithm) {
        this.algorithm = algorithm;
    }

    private static byte[] toToken(String header, String payload) {
        return (header + "." + payload).getBytes();
    }

    private static byte[] decode(String arg) throws Exception {
        String s = arg;
        s = s.replace('-', '+'); // 62nd char of encoding
        s = s.replace('_', '/'); // 63rd char of encoding

        switch (s.length() % 4) // Pad with trailing '='s
        {
            case 0: // No pad chars in this case
                break;

            case 2: // Two pad chars
                s += "==";
                break;

            case 3: // One pad char
                s += "=";
                break;

            default:
                throw new Exception("Illegal base64url string!");
        }

        return TokenDecoder.base64DecodeToByte(s);
    }

    /**
     * Calculate the signature of given header.payload as for
     * <a href="http://tools.ietf.org/html/draft-ietf-jose-json-web-signature-21#appendix-A.2.1">appendix-A.2.1</a>
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String calculate(String header, String payload, PrivateKey signingKey) {
        byte[] token = toToken(header, payload);
        try {
            Signature signature = Signature.getInstance(getAlgorithmInternal());

            signature.initSign(signingKey.getPrivateKey());
            signature.update(token);
            token = signature.sign();

            return TokenDecoder.base64Encode(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ---------- Private methods ---------------------------------------------

    /**
     * Verify the signature of given header.payload as for
     * <a href="http://tools.ietf.org/html/draft-ietf-jose-json-web-signature-21#appendix-A.2.2">appendix-A.2.2</a>
     * <p>
     * {@inheritDoc}
     */
    @Override
    public boolean verify(String signature, String header, String payload, PublicKey verifyingKey) {
        byte[] token = toToken(header, payload);
        try {
            Signature sign = Signature.getInstance(getAlgorithmInternal());
            sign.initVerify(verifyingKey.getPublicKey());
            sign.update(token);

            return sign.verify(decode(signature));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getAlgorithm() {
        return algorithm;
    }

    private String getAlgorithmInternal() {
        String alg = null;
        if (JWSConstants.RS256.equals(algorithm)) {
            alg = "SHA256withRSA";
        } else if (JWSConstants.RS384.equals(algorithm)) {
            alg = "SHA384withRSA";
        } else if (JWSConstants.RS512.equals(algorithm)) {
            alg = "SHA512withRSA";
        }
        return alg;
    }

}
