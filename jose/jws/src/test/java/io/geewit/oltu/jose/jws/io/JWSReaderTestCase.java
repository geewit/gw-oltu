package io.geewit.oltu.jose.jws.io;

import io.geewit.oltu.jose.jws.JWS;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public final class JWSReaderTestCase {

    private JWSReader reader = new JWSReader();

    @Test
    public void parse() {
        JWS jws = reader.read("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImNyaXQiOlsiZXhwIl19.eyJpc3MiOiJqb2UiLA0KICJleHAiOjEzMDA4MTkzODAsDQogImh0dHA6Ly9leGFtcGxlLmNvbS9pc19yb290Ijp0cnVlfQ.dBjftO-_ve-_ve-_vSVP77-9YH3Yre-_ve-_vRbvv70lTWnWv--_vVtYBVhT77-977-9eQ");

        assertEquals("JWT", jws.getHeader().getType());
        assertEquals("HS256", jws.getHeader().getAlgorithm());
        assertArrayEquals(new String[]{"exp"}, jws.getHeader().getCritical());
        assertEquals("{\"iss\":\"joe\",\r\n \"exp\":1300819380,\r\n \"http://example.com/is_root\":true}", jws.getPayload());
        assertEquals("dBjftO-_ve-_ve-_vSVP77-9YH3Yre-_ve-_vRbvv70lTWnWv--_vVtYBVhT77-977-9eQ", jws.getSignature());
    }

}
