package io.geewit.oltu.oauth2.rsfilter;

import java.security.Principal;

public interface OAuthDecision {

    Principal getPrincipal();

    OAuthClient getOAuthClient();

}
