package com.yahoo.myapp.socialphoto.client;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.utils.OAuthEncoder;

/**
 * Created by chitnis on 10/26/15.
 */
public class InstagramApi extends DefaultApi20 {

    private static final String AUTHORIZE_URL = "https://api.instagram.com/oauth/authorize/?"
            + "client_id=%s&redirect_uri=%s&response_type=token";

    private static final String SCOPED_AUTHORIZE_URL = AUTHORIZE_URL + "&scope=%s";

    private static final String ACCESS_TOKEN_URL = "https://api.instagram.com/oauth/access_token";

    @Override
    public String getAccessTokenEndpoint() {
        return ACCESS_TOKEN_URL;
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig oAuthConfig) {
        // Append scope if present
        if (oAuthConfig.hasScope()) {
            return String.format(SCOPED_AUTHORIZE_URL, oAuthConfig.getApiKey(),
                    OAuthEncoder.encode(oAuthConfig.getCallback()),
                    //OAuthEncoder.encode(oAuthConfig.getScope()));
                    OAuthEncoder.encode("likes+comments"));
        } else {
            return String.format(AUTHORIZE_URL, oAuthConfig.getApiKey(),
                    OAuthEncoder.encode(oAuthConfig.getCallback()));
        }
    }
}
