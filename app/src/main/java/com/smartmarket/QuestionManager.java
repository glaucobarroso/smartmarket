package com.smartmarket;

import com.mercadolibre.android.sdk.Identity;

/**
 * Created by Glauco on 16/12/2016.
 */

public class QuestionManager {

    private final String ALL_QUESTIONS_FORMAT_STR = "/questions/search?seller_id=%s&access_token=%s";
    private final String SHIPPING_CALCULATOR_FORMAT_STR = "/items/%s/shipping_options?&zip_code=%s&quantity=%s";

    private Identity mIdentity;

    // private
    public QuestionManager(Identity identity) {
        mIdentity = identity;
    }

    public int answersQuestion() {

    }

    public int getQuestions() {

    }

    public String calculateShipping() {

    }

}
