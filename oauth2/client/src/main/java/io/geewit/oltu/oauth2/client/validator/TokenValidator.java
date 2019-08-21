package io.geewit.oltu.oauth2.client.validator;


import io.geewit.oltu.oauth2.common.OAuth;

/**
 *
 *
 *
 */
public class TokenValidator extends OAuthClientValidator {

    public TokenValidator() {

        requiredParams.put(OAuth.OAUTH_ACCESS_TOKEN, new String[]{});

        notAllowedParams.add(OAuth.OAUTH_CODE);
    }
}
