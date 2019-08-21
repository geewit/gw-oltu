package io.geewit.oltu.oauth2.ext.dynamicreg.server.validator;

import io.geewit.oltu.oauth2.common.OAuth;
import io.geewit.oltu.oauth2.common.exception.OAuthProblemException;
import io.geewit.oltu.oauth2.common.utils.OAuthUtils;
import io.geewit.oltu.oauth2.common.validators.AbstractValidator;
import io.geewit.oltu.oauth2.ext.dynamicreg.common.OAuthRegistration;
import io.geewit.oltu.oauth2.ext.dynamicreg.server.request.JSONHttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class PushPullValidator extends AbstractValidator<JSONHttpServletRequestWrapper> {

    private Logger logger = LoggerFactory.getLogger(PushPullValidator.class);

    public PushPullValidator() {

    }

    public void validateContentType(JSONHttpServletRequestWrapper request) throws OAuthProblemException {

        String contentType = request.getContentType();
        final String expectedContentType = OAuth.ContentType.JSON;
        if (!OAuthUtils.hasContentType(contentType, expectedContentType)) {
            throw OAuthUtils.handleBadContentTypeException(expectedContentType);
        }

//        if content type is json initialize validator for pull or push method based on json content
        initializeValidationParameter(request);

    }

    private void initializeValidationParameter(JSONHttpServletRequestWrapper request)
            throws OAuthProblemException {
        final String requestType = request.getParameter(OAuthRegistration.Request.TYPE);

        if (OAuthUtils.isEmpty(requestType)) {
            throw OAuthUtils.handleOAuthProblemException("Missing [type] parameter value");
        }

        if (OAuthRegistration.Type.PULL.equals(requestType)) {
            requiredParams.add(OAuthRegistration.Request.TYPE);
            requiredParams.add(OAuthRegistration.Request.CLIENT_URL);
        } else if (OAuthRegistration.Type.PUSH.equals(requestType)) {
            requiredParams.add(OAuthRegistration.Request.TYPE);
            requiredParams.add(OAuthRegistration.Request.CLIENT_NAME);
            requiredParams.add(OAuthRegistration.Request.CLIENT_URL);
            requiredParams.add(OAuthRegistration.Request.CLIENT_DESCRIPTION);
            requiredParams.add(OAuthRegistration.Request.REDIRECT_URL);
        } else {
            throw OAuthUtils.handleOAuthProblemException("Invalid [type] parameter value");
        }

        if (logger.isDebugEnabled()) {
            logger.debug("OAuth dynamic client registration type is: {}", new Object[]{requestType});
        }
    }
}
