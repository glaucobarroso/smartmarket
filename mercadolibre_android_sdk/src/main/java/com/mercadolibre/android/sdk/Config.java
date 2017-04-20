package com.mercadolibre.android.sdk;

import android.support.annotation.NonNull;

/**
 * Protected class that holds configurations for the SDK
 */
final class Config {

    /**
     * Prepare and retrieve the Login URL for the given application identifier.
     *
     * @param appId - the application identifier to create the URL for.
     * @param authUrl: The authorization URL. Get from Meli.AuthUrls
     * @return - the created URL as a String value.
     */
    static
    @NonNull
    String getLoginUrlForApplicationIdentifier(@NonNull String appId, @NonNull Meli.AuthUrls authUrl) {
        String login_url = authUrl.getValue();
        login_url = login_url.concat("/authorization?response_type=token&client_id=");
        return login_url.concat(appId);
    }

}
