package com.smartmarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mercadolibre.android.sdk.ApiResponse;
import com.mercadolibre.android.sdk.Identity;
import com.mercadolibre.android.sdk.Meli;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // Request code used to receive callbacks from the SDK
    private static final int REQUEST_CODE = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Meli.initializeSDK(this);
        Meli.startLogin(this, REQUEST_CODE);
        int a = 1;
        new RequestClass(Meli.getCurrentIdentity(this)).start();

    }

    class RequestClass extends Thread {

        Identity mIdentity;

        RequestClass(Identity identity) {
            mIdentity = identity;
        }

        @Override
        public void run() {
            String path = "/sites/MLB/search?seller_id=" + mIdentity.getUserId() + "&access_token=" + mIdentity.getAccessToken().getAccessTokenValue();
            String pathShipping = "/items/MLB820966508/shipping_options?&zip_code=90040060&quantity=1";
            String pathQuestions = "/questions/search?seller_id=" + mIdentity.getUserId() + "&access_token=" + mIdentity.getAccessToken().getAccessTokenValue();
            //ApiResponse response = Meli.get(path);
            //ApiResponse responseShipping = Meli.get(pathShipping);
            ApiResponse responseQuestions = Meli.get(pathQuestions);
            String content = responseQuestions.getContent();
            try {
                JSONObject jsonObject = new JSONObject(content);
                JSONArray questions = (JSONArray) jsonObject.get("questions");
                for(int i = 0; i < questions.length(); i++) {
                    JSONObject question = (JSONObject) questions.get(i);
                    Long id = (Long) question.get("id");
                    String status = (String) question.get("status");
                    if ("UNANSWERED".equals(status)) {
                        answerQuestion(id, "Resposta Teste", mIdentity);
                    }
                }
                int a = 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private ApiResponse answerQuestion(Long questionId, String text, Identity identity) {
        JSONObject answerJson = new JSONObject();
        try {
            answerJson.put("question_id", questionId);
            answerJson.put("text", text);
            String answer = answerJson.toString();
            return Meli.post("/answers", answer, identity);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            Identity identity = Meli.getCurrentIdentity(this);
        }
    }
}
