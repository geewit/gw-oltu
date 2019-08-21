package io.geewit.oltu.oauth2.client.validator;


import io.geewit.oltu.oauth2.common.OAuth;

/**
 *
 *
 *
 */
public class CodeValidator extends OAuthClientValidator {

    public CodeValidator() {

        requiredParams.put(OAuth.OAUTH_CODE, new String[]{});

        notAllowedParams.add(OAuth.OAUTH_ACCESS_TOKEN);
        notAllowedParams.add(OAuth.OAUTH_EXPIRES_IN);
    }
}
