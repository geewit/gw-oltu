package io.geewit.oltu.jose.jws.signature;

final class TestDummySignatureMethod implements SignatureMethod<TestSymetricKey, TestSymetricKey> {

    @Override
    public String calculate(String header, String payload, TestSymetricKey signingKey) {
        return header + payload + signingKey.getValue();
    }

    @Override
    public boolean verify(String signature, String header, String payload, TestSymetricKey verifyingKey) {
        return signature.equals(calculate(header, payload, verifyingKey));
    }

    @Override
    public String getAlgorithm() {
        return "TEST";
    }

}
