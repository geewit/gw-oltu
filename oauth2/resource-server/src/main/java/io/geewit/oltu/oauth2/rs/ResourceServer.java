package io.geewit.oltu.oauth2.rs;

import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;
import io.geewit.oltu.oauth2.common.message.types.ParameterStyle;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;
import io.geewit.oltu.oauth2.common.validators.OAuthValidator;
import io.geewit.oltu.oauth2.rs.extractor.TokenExtractor;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ResourceServer {

    protected Map<ParameterStyle, Class> extractors = new HashMap<>();
    protected Map<ParameterStyle, Class> validators = new HashMap<>();

    /**
     * A replacement for HttpServletRequest.getParameter() as it will mess up with HTTP POST body
     *
     * @param request
     * @param name
     * @return
     */
    public static String[] getQueryParameterValues(HttpServletRequest request, String name) {
        String query = request.getQueryString();
        if (query == null) {
            return null;
        }
        List<String> values = new ArrayList<>();
        String[] params = query.split("&");
        for (String param : params) {
            try {
                param = URLDecoder.decode(param, StandardCharsets.UTF_8.name());
            } catch (UnsupportedEncodingException e) {
                // Ignore
            }
            int index = param.indexOf('=');
            String key = param;
            String value = null;
            if (index != -1) {
                key = param.substring(0, index);
                value = param.substring(index + 1);
            }
            if (key.equals(name)) {
                values.add(value);
            }
        }
        return values.toArray(new String[0]);
    }

    public static String getQueryParameterValue(HttpServletRequest request, String name) {
        String[] values = getQueryParameterValues(request, name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    public OAuthValidator instantiateValidator(ParameterStyle ps) throws OAuthSystemException {
        Class clazz = validators.get(ps);
        if (clazz == null) {
            throw new OAuthSystemException("Cannot instantiate a message validator.");
        }
        return (OAuthValidator) OAuthUtils.instantiateClass(clazz);
    }

    public TokenExtractor instantiateExtractor(ParameterStyle ps) throws OAuthSystemException {
        Class clazz = extractors.get(ps);
        if (clazz == null) {
            throw new OAuthSystemException("Cannot instantiate a token extractor.");
        }
        return (TokenExtractor) OAuthUtils.instantiateClass(clazz);
    }
}
