package io.geewit.oltu.oauth2.client.validator;


import io.geewit.oltu.oauth2.common.OAuth;

/**
 *
 *
 *
 */
public class CodeTokenValidator extends OAuthClientValidator {

    public CodeTokenValidator() {
        requiredParams.put(OAuth.OAUTH_CODE, new String[]{});
        requiredParams.put(OAuth.OAUTH_ACCESS_TOKEN, new String[]{});

        notAllowedParams.add(OAuth.OAUTH_ACCESS_TOKEN);
    }
}
