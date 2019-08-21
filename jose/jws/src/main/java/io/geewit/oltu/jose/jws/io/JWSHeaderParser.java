package io.geewit.oltu.jose.jws.io;

import io.geewit.oltu.commons.json.CustomizableEntityReader;
import io.geewit.oltu.jose.jws.JWS;

final class JWSHeaderParser extends CustomizableEntityReader<JWS, JWS.Builder> implements JWSConstants {

    public JWSHeaderParser(JWS.Builder builder) {
        super(builder);
    }

    @Override
    protected <T> boolean handleProperty(String key, T value) {
        boolean handled = true;

        switch (key) {
            case ALGORITHM:
                getBuilder().setAlgorithm(String.valueOf(value));
                break;
            case JWK_SET_URL:
                getBuilder().setJwkSetUrl(String.valueOf(value));
                break;
            case JSON_WEB_KEY:
                getBuilder().setJsonWebKey(String.valueOf(value));
                break;
            case X509_URL:
                getBuilder().setX509url(String.valueOf(value));
                break;
            case X509_CERTIFICATE_THUMBPRINT:
                getBuilder().setX509CertificateThumbprint(String.valueOf(value));
                break;
            case X509_CERTIFICATE_CHAIN:
                getBuilder().setX509CertificateChain(String.valueOf(value));
                break;
            case KEY_ID:
                getBuilder().setKeyId(String.valueOf(value));
                break;
            case TYPE:
                getBuilder().setType(String.valueOf(value));
                break;
            case CONTENT_TYPE:
                getBuilder().setContentType(String.valueOf(value));
                break;
            case CRITICAL:
                Object[] criticalValues = (Object[]) value;
                String[] critical = new String[criticalValues.length];
                for (int i = 0; i < critical.length; i++) {
                    critical[i] = String.valueOf(criticalValues[i]);
                }
                getBuilder().setCritical(critical);
                break;
            default:
                handled = false;
                break;
        }

        return handled;
    }


}
