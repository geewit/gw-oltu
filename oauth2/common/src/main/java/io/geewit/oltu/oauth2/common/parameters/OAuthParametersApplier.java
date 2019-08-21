package io.geewit.oltu.oauth2.common.parameters;

import io.geewit.oltu.oauth2.common.exception.OAuthSystemException;
import io.geewit.oltu.oauth2.common.message.OAuthMessage;

import java.util.Map;

/**
 * Applies given parameters to the OAuth message.
 * Provided implementations include OAuth parameters in one of those:
 * <ul>
 * <li>HTTP request URI Query</li>
 * <li>HTTP request entity-body with application/x-www-form-urlencoded encoding</li>
 * <li>HTTP request entity-body with application/json encoding</li>
 * <li>HTTP request Authorization/WWW-Authenticate header</li>
 * </ul>
 * Additional implementations can be provided.
 *
 *
 *
 *
 */
public interface OAuthParametersApplier {

    OAuthMessage applyOAuthParameters(OAuthMessage message, Map<String, Object> params) throws OAuthSystemException;
}
