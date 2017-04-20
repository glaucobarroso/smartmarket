package com.smartmarket;

import android.content.res.Resources;

import com.google.gson.Gson;
import com.mercadolibre.android.sdk.AccessToken;
import com.mercadolibre.android.sdk.ApiResponse;
import com.mercadolibre.android.sdk.Identity;
import com.mercadolibre.android.sdk.Meli;
import com.smartmarket.data.question.Questions;
import com.smartmarket.data.question.Shipping;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Glauco on 16/12/2016.
 */

public class QuestionManager extends Manager{

    public final static String UNANSWERED = "UNANSWERED";
    public final static String EXPRESSO = "Expresso";
    private final String ALL_QUESTIONS_FORMAT_STR = "/questions/search?seller_id=%s&status=%s&access_token=%s";
    private final String SHIPPING_CALCULATOR_FORMAT_STR = "/items/%s/shipping_options?&zip_code=%s&quantity=%s";
    private final String QUESTIONS_BY_USER_FORMAT_STR = "/questions/search?item=%s&from=%s";
    private final String ANSWER_PATH = "/answers";
    private final String QUESTION_ID_TAG = "question_id";
    private final String QUESTION_TEXT_TAG = "text";
    private final String CEP_REGEX = "\\d{5}[-\\s/]*\\d{3}";

    private final String HOUR_UNIT = "hour";
    public final int DAY_HOURS = 24;

    public QuestionManager(Identity identity) {
        super(identity);
    }

    public int answerQuestion(Long questionId, String text) {
        JSONObject answerJson = new JSONObject();
        try {
            answerJson.put(QUESTION_ID_TAG, questionId);
            answerJson.put(QUESTION_TEXT_TAG, text);
            String answer = answerJson.toString();
            String answerUTF8 = new String(answer.getBytes("UTF-8"));
            ApiResponse response = Meli.post(ANSWER_PATH, answerUTF8, mIdentity);
            return response.getResponseCode();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ApiResponse.ApiResponseCode.RESPONSE_CODE_ERROR;
    }

    public List<Questions.Question> getQuestions() {
        if (mIdentity != null) {
            String content = get(ALL_QUESTIONS_FORMAT_STR, mIdentity.getUserId(), UNANSWERED, mIdentity.getAccessToken().getAccessTokenValue());
            if (content != null) {
                Questions questions = mGson.fromJson(content, Questions.class);
                return questions.getQuestions();
            }
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

    public String getCep(String question) {
        question = question.replace(".", "");
        Pattern pattern = Pattern.compile(CEP_REGEX);
        Matcher matcher = pattern.matcher(question);
        if (matcher.find()) {
            String cep = matcher.group();
            if (cep != null) {
                return cep.replaceAll("[^\\d.]", "");
            }
        }
        return null;
    }

    public List<Questions.Question> getQuestionsByUser(String itemId, Integer buyerId) {
        String content = get(QUESTIONS_BY_USER_FORMAT_STR, itemId, String.valueOf(buyerId));
        if (content != null) {
            Questions questions = mGson.fromJson(content, Questions.class);
            return questions.getQuestions();
        }
        return null;
    }

    public String buildShippingAnswer(Resources resources, List<Shipping.Option> shippingOptions) {
        StringBuilder builder = new StringBuilder();
        int daysCount = 1;
        Double expressoPrice = Double.valueOf(-1);
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

                        boolean append = false;
                        // ugly workaround to avoid suggestions with the same price
                        // TODO improve this PLEASE
                        if (EXPRESSO.equalsIgnoreCase(option.getName())) {
                            expressoPrice = cost;
                            append = true;
                        } else if (expressoPrice.doubleValue() != cost.doubleValue()) {
                            append = true;
                        }

                        if (append) {
                            String answer = String.format(resources.getQuantityString(R.plurals.shipping_answer, daysCount, option.getName(), costStr, shippingTimeDays, shippingTimeDays + shippingTimeOffsetDays));
                            daysCount = 1;
                            builder.append(answer);
                            builder.append(" ");
                        }

                    }
                }
            }
        }
        return builder.toString();
    }

}
