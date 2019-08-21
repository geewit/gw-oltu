package io.geewit.oltu.oauth2.rsfilter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

/**
 *
 */
public class OAuthUtils {

    public static <T> T initiateServletContext(FilterConfig config, String key, Class<T> expectedClass)
            throws ServletException {

        T provider = (T) config.getServletContext().getAttribute(key);

        if (provider != null) {
            return provider;
        }

        provider = loadObject(config, key, expectedClass);

        // set the provider and validator
        config.getServletContext().setAttribute(key, provider);

        return provider;
    }

    public static <T> T loadObject(FilterConfig config, String classParamName, Class<T> expectedClass)
            throws ServletException {

        T ob;

        String providerClassName = config.getInitParameter(classParamName);
        if (isEmpty(providerClassName)) {
            throw new ServletException(classParamName + " context param required");
        }
        try {
            Class<T> clazz = (Class<T>) Class.forName(providerClassName);
            if (!expectedClass.isAssignableFrom(clazz)) {
                throw new ServletException(classParamName + " class: " + providerClassName
                        + " must be an instance of: " + expectedClass.getName());
            }
            ob = createObjectFromClassName(clazz);
        } catch (ClassNotFoundException e) {
            throw new ServletException(classParamName + " class " + providerClassName + " not found");
        } catch (Exception e) {
            throw new ServletException("Cannot instantiate: " + providerClassName);
        }
        return ob;
    }

    public static <T> T createObjectFromClassName(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }

    public static boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
