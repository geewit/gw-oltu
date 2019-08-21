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

import io.geewit.oltu.oauth2.common.message.OAuthMessage;
import io.geewit.oltu.oauth2.common.utils.JSONUtils;
import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.utils.DummyOAuthMessage;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 *
 *
 *
 */
public class JSONBodyParametersApplierTest {

    @Test
    public void testApplyOAuthParameters() throws Exception {

        OAuthParametersApplier app = new JSONBodyParametersApplier();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put(OAuth.OAUTH_EXPIRES_IN, 3600l);
        params.put(OAuth.OAUTH_ACCESS_TOKEN, "token_authz");
        params.put(OAuth.OAUTH_CODE, "code_");
        params.put(OAuth.OAUTH_SCOPE, "read");
        params.put(OAuth.OAUTH_STATE, "state");
        params.put("empty_param", "");
        params.put("null_param", null);
        params.put("", "some_value");
        params.put(null, "some_value");

        OAuthMessage message = new DummyOAuthMessage("http://www.example.com/rd", 200);
        app.applyOAuthParameters(message, params);

        String msgBody = message.getBody();
        Map<String, Object> map = JSONUtils.parseJSON(msgBody);
        assertEquals(3600L, map.get(OAuth.OAUTH_EXPIRES_IN));
        assertEquals("token_authz", map.get(OAuth.OAUTH_ACCESS_TOKEN));
        assertEquals("code_", map.get(OAuth.OAUTH_CODE));
        assertEquals("read", map.get(OAuth.OAUTH_SCOPE));
        assertEquals("state", map.get(OAuth.OAUTH_STATE));
        assertNull(map.get("empty_param"));
        assertNull(map.get("null_param"));
        assertNull(map.get(""));
        assertNull(map.get(null));
    }
}
