package io.geewit.oltu.jose.jws.io;

import io.geewit.oltu.jose.jws.JWS;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class JWSWriterTestCase {

    private final JWSWriter jwsWriter = new JWSWriter();

    @Test
    public void serialize() {
        JWS jws = new JWS.Builder()
                .setAlgorithm("HS256")
                .setType("JWT")
                .setCritical(new String[]{"exp"})
                .setPayload("{\"iss\":\"joe\",\r\n \"exp\":1300819380,\r\n \"http://example.com/is_root\":true}")
                .setSignature("dBjftO-_ve-_ve-_vSVP77-9YH3Yre-_ve-_vRbvv70lTWnWv--_vVtYBVhT77-977-9eQ")
                .build();
        String actual = jwsWriter.write(jws);
        assertEquals("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImNyaXQiOlsiZXhwIl19.eyJpc3MiOiJqb2UiLA0KICJleHAiOjEzMDA4MTkzODAsDQogImh0dHA6Ly9leGFtcGxlLmNvbS9pc19yb290Ijp0cnVlfQ.dBjftO-_ve-_ve-_vSVP77-9YH3Yre-_ve-_vRbvv70lTWnWv--_vVtYBVhT77-977-9eQ",
                actual);
    }

}
