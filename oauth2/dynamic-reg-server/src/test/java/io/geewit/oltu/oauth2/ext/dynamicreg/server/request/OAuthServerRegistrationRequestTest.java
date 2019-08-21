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
package io.geewit.oltu.oauth2.ext.dynamicreg.server.request;

import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.utils.test.FileUtils;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class OAuthServerRegistrationRequestTest {

    @Test
    public void testValidOAuthPushRequest() throws Exception {
        final String validJson = FileUtils.readTextFileAsString("json/push_valid.json");

        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/oauth/register");
        request.setContentType(OAuth.ContentType.JSON);
        request.setContent(validJson.getBytes("UTF-8"));

        final JSONHttpServletRequestWrapper jsonWrapper = new JSONHttpServletRequestWrapper(request);
        OAuthServerRegistrationRequest registrationRequest = new OAuthServerRegistrationRequest(jsonWrapper);

        assertEquals("Uploading and also editing capabilities!",
                registrationRequest.getClientDescription());
        assertEquals("http://onlinephotogallery.com/icon.png", registrationRequest.getClientIcon());
        assertEquals("Online Photo Gallery", registrationRequest.getClientName());
        assertEquals("https://onlinephotogallery.com/client_reg", registrationRequest.getRedirectURI());
        assertEquals("push", registrationRequest.getType());
        assertEquals("http://onlinephotogallery.com", registrationRequest.getClientUrl());
    }

    @Test(expected = OAuthProblemException.class)
    public void testInvalidContentTypePushRequest() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/oauth/register");
        request.setContentType(OAuth.ContentType.URL_ENCODED);

        final JSONHttpServletRequestWrapper jsonWrapper = new JSONHttpServletRequestWrapper(request);
        OAuthServerRegistrationRequest registrationRequest = new OAuthServerRegistrationRequest(jsonWrapper);
    }

    @Test(expected = OAuthProblemException.class)
    public void testInvalidMethodPushRequest() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/oauth/register");
        request.setContentType(OAuth.ContentType.JSON);

        final JSONHttpServletRequestWrapper jsonWrapper = new JSONHttpServletRequestWrapper(request);
        OAuthServerRegistrationRequest registrationRequest = new OAuthServerRegistrationRequest(jsonWrapper);
    }

    @Test(expected = OAuthProblemException.class)
    public void testInvalidBodyPushRequest() throws Exception {
        final String inValidJson = FileUtils.readTextFileAsString("json/push_invalid.json");
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/oauth/register");
        request.setContentType(OAuth.ContentType.JSON);
        request.setContent(inValidJson.getBytes("UTF-8"));

        final JSONHttpServletRequestWrapper jsonWrapper = new JSONHttpServletRequestWrapper(request);
        OAuthServerRegistrationRequest registrationRequest = new OAuthServerRegistrationRequest(jsonWrapper);
    }
}
