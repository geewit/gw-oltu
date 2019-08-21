package io.geewit.oltu.jose.jws.io;

import io.geewit.oltu.commons.json.CustomizableEntityWriter;
import io.geewit.oltu.jose.jws.Header;

public final class JWSHeaderWriter extends CustomizableEntityWriter<Header> implements JWSConstants {

    @Override
    protected void handleProperties(Header header) {
        set(ALGORITHM, header.getAlgorithm());
        set(JWK_SET_URL, header.getJwkSetUrl());
        set(JSON_WEB_KEY, header.getJsonWebKey());
        set(X509_URL, header.getX509url());
        set(X509_CERTIFICATE_THUMBPRINT, header.getX509CertificateThumbprint());
        set(X509_CERTIFICATE_CHAIN, header.getX509CertificateChain());
        set(KEY_ID, header.getKeyId());
        set(TYPE, header.getType());
        set(CONTENT_TYPE, header.getContentType());
        set(CRITICAL, header.getCritical());
    }

}
