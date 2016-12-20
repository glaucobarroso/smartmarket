package com.smartmarket;

import android.content.res.Resources;

import com.google.gson.Gson;
import com.mercadolibre.android.sdk.ApiResponse;
import com.mercadolibre.android.sdk.Identity;
import com.mercadolibre.android.sdk.Meli;
import com.smartmarket.data.question.Questions;
import com.smartmarket.data.question.Shipping;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Glauco on 16/12/2016.
 */

public class QuestionManager extends Manager{

    private final String ALL_QUESTIONS_FORMAT_STR = "/questions/search?seller_id=%s&access_token=%s";
    private final String SHIPPING_CALCULATOR_FORMAT_STR = "/items/%s/shipping_options?&zip_code=%s&quantity=%s";
    private final String ANSWER_PATH = "/answers";
    private final String QUESTION_ID_TAG = "question_id";
    private final String QUESTION_TEXT_TAG = "text";

    private final String HOUR_UNIT = "hour";
    public final int DAY_HOURS = 24;

    public QuestionManager(Identity identity) {
        super(identity);
    }

    private int answerQuestion(Long questionId, String text) {
        JSONObject answerJson = new JSONObject();
        try {
            answerJson.put(QUESTION_ID_TAG, questionId);
            answerJson.put(QUESTION_TEXT_TAG, text);
            String answer = answerJson.toString();
            ApiResponse response = Meli.post(ANSWER_PATH, answer, mIdentity);
            return response.getResponseCode();
        } catch (JSONException e) {
            e.printStackTrace();
            return ApiResponse.ApiResponseCode.RESPONSE_CODE_ERROR;
        }
    }

    public List<Questions.Question> getQuestions() {
        String content = get(ALL_QUESTIONS_FORMAT_STR, mIdentity.getUserId(), mIdentity.getAccessToken().getAccessTokenValue());
        if (content != null) {
            Questions questions = mGson.fromJson(content, Questions.class);
            return questions.getQuestions();
        }
        return null;
    }

    public List<Shipping.Option> getShippingOptions(String ... args) {
        String content = get(SHIPPING_CALCULATOR_FORMAT_STR, args);
        if (content != null) {
            Shipping shipping = mGson.fromJson(content, Shipping.class);
            return shipping.getOptions();
        }
        return null;
    }

    public String buildShippingAnswer(Resources resources, List<Shipping.Option> shippingOptions) {
        StringBuilder builder = new StringBuilder();
        int daysCount = 1;
        for(Shipping.Option option : shippingOptions) {
            Double cost = option.getCost();
            Shipping.EstimatedDeliveryTime estimatedDelivery = option.getEstimatedDeliveryTime();
            if (estimatedDelivery != null) {
                String unit = estimatedDelivery.getUnit();
                if (unit != null) {
                    if (HOUR_UNIT.equalsIgnoreCase(unit)) {
                        int shippingTimeDays = estimatedDelivery.getShipping() / DAY_HOURS;
                        Integer shippingTimeOffset = estimatedDelivery.getOffset().getShipping();
                        int shippingTimeOffsetDays = 0;
                        if (shippingTimeOffset != null) {
                            daysCount++;
                            shippingTimeOffsetDays = (shippingTimeOffset) / DAY_HOURS;
                        }
                        // ugly workaround to force float with comma no matter which is the device's locale
                        String costStr = String.format("%.02f", cost).replace('.', ',');

                        String answer = String.format(resources.getQuantityString(R.plurals.shipping_answer, daysCount, option.getName(), costStr, shippingTimeDays, shippingTimeDays + shippingTimeOffsetDays));
                        builder.append(answer);
                        builder.append(" ");
                    }
                }
            }
        }
        return builder.toString();
    }

}
