/**
 * Copyright 2010 Newcastle University
 * <p>
 * http://research.ncl.ac.uk/smart/
 * <p>
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geewit.oltu.oauth2.integration;

import io.geewit.oltu.oauth2.client.URLConnectionClient;
import io.geewit.oltu.oauth2.client.request.OAuthClientRequest;
import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;
import io.geewit.oltu.oauth2.ext.dynamicreg.client.OAuthRegistrationClient;
import io.geewit.oltu.oauth2.ext.dynamicreg.client.request.OAuthClientRegistrationRequest;
import io.geewit.oltu.oauth2.ext.dynamicreg.client.response.OAuthClientRegistrationResponse;
import io.geewit.oltu.oauth2.ext.dynamicreg.common.OAuthRegistration;
import org.junit.Test;

/**
 *
 *
 *
 */
public class ClientRegistrationTest extends ClientServerOAuthTest {

    @Test
    public void testPushMetadataRegistration() throws Exception {

        OAuthClientRequest request = OAuthClientRegistrationRequest
                .location(CommonExt.REGISTRATION_ENDPOINT, OAuthRegistration.Type.PUSH)
                .setName(CommonExt.APP_NAME)
                .setUrl(CommonExt.APP_URL)
                .setDescription(CommonExt.APP_DESCRIPTION)
                .setIcon(CommonExt.APP_ICON)
                .setRedirectURL(CommonExt.APP_REDIRECT_URI)
                .buildJSONMessage();

        OAuthRegistrationClient oauthclient = new OAuthRegistrationClient(new URLConnectionClient());
        OAuthClientRegistrationResponse response = oauthclient.clientInfo(request);

        assertEquals(CommonExt.CLIENT_ID, response.getClientId());
        assertEquals(CommonExt.CLIENT_SECRET, response.getClientSecret());
        assertEquals(CommonExt.EXPIRES_IN, response.getExpiresIn());
        assertEquals(CommonExt.ISSUED_AT, response.getIssuedAt());

    }

    @Test(expected = OAuthSystemException.class)
    public void testInvalidType() throws Exception {

        OAuthClientRequest request = OAuthClientRegistrationRequest
                .location(CommonExt.REGISTRATION_ENDPOINT, "unknown_type")
                .setName(CommonExt.APP_NAME)
                .setUrl(CommonExt.APP_URL)
                .setDescription(CommonExt.APP_DESCRIPTION)
                .setIcon(CommonExt.APP_ICON)
                .setRedirectURL(CommonExt.APP_REDIRECT_URI)
                .buildBodyMessage();

        OAuthRegistrationClient oauthclient = new OAuthRegistrationClient(new URLConnectionClient());
        OAuthClientRegistrationResponse response = oauthclient.clientInfo(request);

    }

}
