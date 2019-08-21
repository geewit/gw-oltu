package io.geewit.oltu.jose.jws.signature.impl;

import io.geewit.oltu.commons.token.TokenDecoder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SignatureMethodsHMAC256ImplTest {

    private final byte[] hsKey = {3, (byte) 35, (byte) 53, (byte) 75,
            (byte) 43, (byte) 15, (byte) 165, (byte) 188, (byte) 131,
            (byte) 126, (byte) 6, (byte) 101, (byte) 119, (byte) 123,
            (byte) 166, (byte) 143, (byte) 90, (byte) 179, (byte) 40,
            (byte) 230, (byte) 240, (byte) 84, (byte) 201, (byte) 40,
            (byte) 169, (byte) 15, (byte) 132, (byte) 178, (byte) 210,
            (byte) 80, (byte) 46, (byte) 191, (byte) 211, (byte) 251,
            (byte) 90, (byte) 146, (byte) 210, (byte) 6, (byte) 71, (byte) 239,
            (byte) 150, (byte) 138, (byte) 180, (byte) 195, (byte) 119,
            (byte) 98, (byte) 61, (byte) 34, (byte) 61, (byte) 46, (byte) 33,
            (byte) 114, (byte) 5, (byte) 46, (byte) 79, (byte) 8, (byte) 192,
            (byte) 205, (byte) 154, (byte) 245, (byte) 103, (byte) 208,
            (byte) 128, (byte) 163};

    private String hs256;

    private String payload;

    private SymmetricKeyImpl key;

    private SignatureMethodsHMAC256Impl sHmacImpl;

    @Before
    public void setUp() {
        payload = "{\"iss\":\"joe\",\r\n \"exp\":1300819380,\r\n \"http://example.com/is_root\":true}";
        hs256 = "{\"typ\":\"JWT\",\r\n" + " \"alg\":\"HS256\"}";
        key = new SymmetricKeyImpl(hsKey);
        sHmacImpl = new SignatureMethodsHMAC256Impl();
    }

    @After
    public void tearDown() {
        payload = null;
        hs256 = null;
        key = null;
        sHmacImpl = null;
    }

    @Test
    public void testCalculate() {
        assertEquals("dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk",
                sHmacImpl.calculate(TokenDecoder.base64Encode(hs256),
                        TokenDecoder.base64Encode(payload), key));
    }

    @Test
    public void testVerify() {
        String accessToken = "eyJ0eXAiOiJKV1QiLA0KICJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJqb2UiLA0KICJleHAiOjEzMDA4MTkzODAsDQogImh0dHA6Ly9leGFtcGxlLmNvbS9pc19yb290Ijp0cnVlfQ.dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk";
        String jwt[] = accessToken.split("\\.");
        assertTrue(sHmacImpl.verify(jwt[2], jwt[0], jwt[1], key));
    }

}
