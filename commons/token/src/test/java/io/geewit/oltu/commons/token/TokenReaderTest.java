package io.geewit.oltu.commons.token;

import org.junit.Assert;
import org.junit.Test;

public class TokenReaderTest {

    private TokenReader tokenReader;

    @Test
    public void test_read() {
        tokenReader = new TokenReader<String>() {
            protected String build(String rawString, String decodedHeader,
                                   String decodedBody, String encodedSignature) {

                return "";
            }
        };

        String accessToken = "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJqb2UiLA0KICJleHAiOjEzMDA4MTkzODAsDQogImh0dHA6Ly9leGFtcGxlLmNvbS9pc19yb290Ijp0cnVlfQ.cC4hiUPoj9Eetdgtv3hF80EGrhuB__dzERat0XF9g2VtQgr9PJbu3XOiZj5RZmh7AAuHIm4Bh-0Qc_lF5YKt_O8W2Fp5jujGbds9uJdbF9CUAr7t1dnZcAcQjbKBYNX4BAynRFdiuB--f_nZLgrnbyTyWzO75vRK5h6xBArLIARNPvkSjtQBMHlb1L07Qe7K0GarZRmB_eSN9383LcOLn6_dO--xi12jzDwusC-eOkHWEsqtFZESc6BfI7noOPqvhJ1phCnvWh6IeYI2w9QOYEUipUTI8np6LbgGY9Fs98rqVt5AXLIhWkWywlVmtVrBp0igcN_IoypGlUPQGe77Rw";
        Assert.assertNotNull(tokenReader.read(accessToken));
    }

    @Test
    public void test_read2() {
        tokenReader = new TokenReader<String>() {
            protected String build(String rawString, String decodedHeader,
                                   String decodedBody, String encodedSignature) {
                return null;
            }
        };
        String accessToken = "BadToken";
        try {
            tokenReader.read(accessToken);
            Assert.fail("failed test");
        } catch (IllegalArgumentException ignored) {
            //expected
        }

    }
}
