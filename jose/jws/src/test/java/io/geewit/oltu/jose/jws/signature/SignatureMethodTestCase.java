package io.geewit.oltu.jose.jws.signature;

import io.geewit.oltu.commons.token.TokenDecoder;
import io.geewit.oltu.jose.jws.JWS;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class SignatureMethodTestCase {

    private String hs256;

    private String payload;

    private TestSymetricKey key;

    private String signature;

    private TestDummySignatureMethod method;

    @Before
    public void setUp() {
        payload = "{\"iss\":\"joe\",\r\n \"exp\":1300819380,\r\n \"http://example.com/is_root\":true}";
        hs256 = "{\"alg\":\"TEST\",\"typ\":\"JWT\"}";

        key = new TestSymetricKey("supercalifragilistichespiralidoso1234567890");
        signature = TokenDecoder.base64Encode(hs256) + TokenDecoder.base64Encode(payload) + key.getValue();

        method = new TestDummySignatureMethod();
    }

    @After
    public void tearDown() {
        payload = null;
        key = null;
        signature = null;
        method = null;
    }

    @Test
    public void simpleSignatureVerification() {
        assertEquals(hs256 + payload + key.getValue(), method.calculate(hs256, payload, key));
        assertTrue(method.verify(hs256 + payload + key.getValue(), hs256, payload, key));
    }

    @Test
    public void signJWS() {
        JWS jws = new JWS.Builder()
                .setType("JWT").
                        setAlgorithm("TEST")
                .setPayload(payload)
                .sign(method, key)
                .build();

        assertEquals("TEST", jws.getHeader().getAlgorithm());
        assertEquals(signature, jws.getSignature());
    }

    @Test
    public void validateJWS() {
        JWS jws = new JWS.Builder()
                .setType("JWT")
                .setAlgorithm("TEST")
                .setPayload(payload)
                .sign(method, key)
                .build();
        assertTrue(jws.validate(method, key));
    }

}
