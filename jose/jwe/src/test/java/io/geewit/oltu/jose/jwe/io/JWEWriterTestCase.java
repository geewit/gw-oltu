package io.geewit.oltu.jose.jwe.io;

import io.geewit.oltu.jose.jwe.JWE;
import io.geewit.oltu.jose.jwe.JWEConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JWEWriterTestCase {

    private final JWEWriter jweWriter = new JWEWriter();

    @Test
    public void serialize() {
        JWE jwe = new JWE.Builder().setAlgorithm(JWEConstants.A128KW)
                .setEncryptionAlgorithm(JWEConstants.A128CBC_HS256)
                .setEncryptedKey("6KB707dM9YTIgHtLvtgWQ8mKwboJW3of9locizkDTHzBC2IlrT1oOQ")
                .setContentEncryption("AxY8DCtDaGlsbGljb3RoZQ.KDlTtXchhZTGufMYmOYGS4HffxPSUrfmqCHXaI9wOGY.U0m_YmjN04DJvceFICbCVQ")
                .build();
        String actual = jweWriter.write(jwe);
        String specJWE = "eyJhbGciOiJBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0." +
                "6KB707dM9YTIgHtLvtgWQ8mKwboJW3of9locizkDTHzBC2IlrT1oOQ." +
                "AxY8DCtDaGlsbGljb3RoZQ." +
                "KDlTtXchhZTGufMYmOYGS4HffxPSUrfmqCHXaI9wOGY." +
                "U0m_YmjN04DJvceFICbCVQ";
        assertEquals(specJWE,
                actual);
    }
}
