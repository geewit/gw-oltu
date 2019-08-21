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
package io.geewit.oltu.oauth2.common.message;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 *
 *
 */
public class OAuthResponseTest {

    @Test
    public void testErrorResponse() throws Exception {
        OAuthResponse oAuthResponse = OAuthResponse.errorResponse(400)
                .setError("error")
                .setRealm("album")
                .setState("ok")
                .setErrorDescription("error_description")
                .setErrorUri("http://example-uri")
                .setParam("param", "value")
                .buildJSONMessage();

        String body = oAuthResponse.getBody();
        assertEquals(
                "{\"param\":\"value\",\"error_description\":\"error_description\",\"realm\":\"album\",\"state\":\"ok\",\"error\":\"error\",\"error_uri\":\"http://example-uri\"}",
                body);
    }

}
