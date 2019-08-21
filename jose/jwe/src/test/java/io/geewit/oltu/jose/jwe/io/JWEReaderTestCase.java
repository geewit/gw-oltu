package io.geewit.oltu.jose.jwe.io;

import io.geewit.oltu.jose.jwe.JWE;
import io.geewit.oltu.jose.jwe.JWEConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JWEReaderTestCase {

    private JWEReader reader = new JWEReader();

    @Test
    public void parse() {
        String specJWE = "eyJhbGciOiJBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0." +
                "6KB707dM9YTIgHtLvtgWQ8mKwboJW3of9locizkDTHzBC2IlrT1oOQ." +
                "AxY8DCtDaGlsbGljb3RoZQ." +
                "KDlTtXchhZTGufMYmOYGS4HffxPSUrfmqCHXaI9wOGY." +
                "U0m_YmjN04DJvceFICbCVQ";
        JWE jwe = reader.read(specJWE);

        assertEquals(JWEConstants.A128KW, jwe.getHeader().getAlgorithm());
        assertEquals(JWEConstants.A128CBC_HS256, jwe.getHeader().getEncryptionAlgorithm());
        assertEquals("6KB707dM9YTIgHtLvtgWQ8mKwboJW3of9locizkDTHzBC2IlrT1oOQ", jwe.getEncryptedKey());
        assertEquals("AxY8DCtDaGlsbGljb3RoZQ.KDlTtXchhZTGufMYmOYGS4HffxPSUrfmqCHXaI9wOGY.U0m_YmjN04DJvceFICbCVQ", jwe.getContentEncryption());
    }

}
