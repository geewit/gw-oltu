/**
 * Copyright 2011 Newcastle University
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
import io.geewit.oltu.oauth2.common.exception.OAuthRuntimeException;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.*;
import javax.json.JsonValue.ValueType;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.StringReader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static java.lang.String.format;

/**
 *
 */
public class JSONHttpServletRequestWrapper extends HttpServletRequestWrapper {

    final Map<String, String[]> parameters = new HashMap<>();
    private Logger log = LoggerFactory.getLogger(JSONHttpServletRequestWrapper.class);
    private boolean bodyRead = false;

    public JSONHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    private static String toJavaObject(JsonValue jsonValue) {
        String value = null;

        switch (jsonValue.getValueType()) {
            case FALSE:
                value = Boolean.FALSE.toString();
                break;

            case NULL:
                value = null;
                break;

            case NUMBER:
                JsonNumber jsonNumber = (JsonNumber) jsonValue;
                value = jsonNumber.numberValue().toString();
                break;

            case OBJECT:
                // not supported in this version
                break;

            case STRING:
                JsonString jsonString = (JsonString) jsonValue;
                value = jsonString.getString();
                break;

            case TRUE:
                value = Boolean.TRUE.toString();
                break;

            default:
                break;
        }

        return value;
    }

    public String getParameter(String name) {
        final String[] values = getParameterMap().get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    public Map<String, String[]> getParameterMap() {
        if (!bodyRead) {
            String body = readJsonBody();

            StringReader reader = new StringReader(body);
            JsonReader jsonReader = Json.createReader(reader);
            JsonStructure structure = jsonReader.read();

            if (structure == null || structure instanceof JsonArray) {
                throw new IllegalArgumentException(format("String '%s' is not a valid JSON object representation",
                        body));
            }

            JsonObject object = (JsonObject) structure;
            for (Entry<String, JsonValue> entry : object.entrySet()) {
                String key = entry.getKey();
                if (key != null) {
                    JsonValue jsonValue = entry.getValue();

                    // guard from null values
                    if (jsonValue != null) {
                        String[] values;

                        if (ValueType.ARRAY == jsonValue.getValueType()) {
                            JsonArray array = (JsonArray) jsonValue;
                            values = new String[array.size()];
                            for (int i = 0; i < array.size(); i++) {
                                JsonValue current = array.get(i);
                                values[i] = toJavaObject(current);
                            }
                        } else {
                            values = new String[]{toJavaObject(jsonValue)};
                        }

                        parameters.put(key, values);
                    }
                }
            }

            jsonReader.close();
        }

        return Collections.unmodifiableMap(parameters);
    }

    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(getParameterMap().keySet());
    }

    public String[] getParameterValues(String name) {
        return getParameterMap().get(name);
    }

    /**
     * Lazily read JSON from request
     *
     * @throws OAuthProblemException
     */
    private String readJsonBody() {
        try {
            final ServletRequest request = getRequest();
            String contentType = request.getContentType();
            final String expectedContentType = OAuth.ContentType.JSON;
            if (!OAuthUtils.hasContentType(contentType, expectedContentType)) {
                return "";
            }

            final ServletInputStream inputStream = request.getInputStream();
            if (inputStream == null) {
                return "";
            }

            bodyRead = true;
            return OAuthUtils.saveStreamAsString(inputStream);
        } catch (Exception e) {
            log.error("Dynamic client registration error: ", e);
            throw new OAuthRuntimeException("OAuth server error");
        }
    }
}
