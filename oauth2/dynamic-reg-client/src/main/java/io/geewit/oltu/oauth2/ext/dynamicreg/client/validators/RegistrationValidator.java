package io.geewit.oltu.oauth2.ext.dynamicreg.client.validators;

import io.geewit.oltu.oauth2.client.response.OAuthClientResponse;
import io.geewit.oltu.oauth2.client.validator.OAuthClientValidator;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;
import io.geewit.oltu.oauth2.ext.dynamicreg.common.OAuthRegistration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class RegistrationValidator extends OAuthClientValidator {

    protected Map<String, String[]> optionalParams = new HashMap<>();

    public RegistrationValidator() {
        requiredParams.put(OAuthRegistration.Response.CLIENT_ID, new String[]{});

        optionalParams
                .put(OAuthRegistration.Response.ISSUED_AT, new String[]{OAuthRegistration.Response.EXPIRES_IN});
    }


    private void validateOptionalParams(OAuthClientResponse response) throws OAuthProblemException {
        Set<String> missingParameters = new HashSet<>();

        for (Map.Entry<String, String[]> requiredParam : optionalParams.entrySet()) {
            String paramName = requiredParam.getKey();
            String val = response.getParam(paramName);
            if (!OAuthUtils.isEmpty(val)) {
                String[] dependentParams = requiredParam.getValue();
                if (!OAuthUtils.hasEmptyValues(dependentParams)) {
                    for (String dependentParam : dependentParams) {
                        val = response.getParam(dependentParam);
                        if (OAuthUtils.isEmpty(val)) {
                            missingParameters.add(dependentParam);
                        }
                    }
                }
            }

        }

        if (!missingParameters.isEmpty()) {
            throw OAuthUtils.handleMissingParameters(missingParameters);
        }
    }

    @Override
    public void validateParameters(OAuthClientResponse response) throws OAuthProblemException {
        super.validateParameters(response);
        validateOptionalParams(response);
    }
}
