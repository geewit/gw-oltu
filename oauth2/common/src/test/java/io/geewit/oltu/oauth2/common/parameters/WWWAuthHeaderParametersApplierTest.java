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

package io.geewit.oltu.oauth2.common.parameters;

import io.geewit.oltu.oauth2.common.message.OAuthResponse;
import io.geewit.oltu.oauth2.common.OAuth;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 *
 *
 */
public class WWWAuthHeaderParametersApplierTest {

    @Test
    public void testApplyOAuthParameters() throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("error", "invalid_token");
        params.put("error_uri", "http://www.example.com/error");
        params.put("scope", "s1 s2 s3");
        params.put("empty_param", "");
        params.put("null_param", null);
        params.put("", "some_value");
        params.put(null, "some_value");

        OAuthResponse res = OAuthResponse.status(200).location("").buildQueryMessage();

        OAuthParametersApplier applier = new WWWAuthHeaderParametersApplier();
        res = (OAuthResponse) applier.applyOAuthParameters(res, params);
        assertNotNull(res);
        String header = res.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE);
        assertNotNull(header);
        assertEquals(OAuth.OAUTH_HEADER_NAME
                        + " scope=\"s1 s2 s3\",error=\"invalid_token\",error_uri=\"http://www.example.com/error\"",
                header);
    }

}
