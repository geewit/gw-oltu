package io.geewit.oltu.jose.jws.signature;

final class TestSymetricKey implements SymmetricKey {

    private final String value;

    public TestSymetricKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
