package com.smartmarket;

import com.google.gson.Gson;
import com.mercadolibre.android.sdk.ApiResponse;
import com.mercadolibre.android.sdk.Identity;
import com.mercadolibre.android.sdk.Meli;

/**
 * Created by Glauco on 19/12/2016.
 */

public class Manager {

    protected Gson mGson;
    protected Identity mIdentity;

    public Manager(Identity identity) {
        mGson = new Gson();
        mIdentity = identity;
    }

    public String get(String formatString, String ... args) {
        String path = String.format(formatString, args);
        ApiResponse response = Meli.get(path);
        if (response.getResponseCode() == ApiResponse.ApiResponseCode.RESPONSE_CODE_SUCCESS) {
            return response.getContent();
        }
        return null;
    }

}
