package com.smartmarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mercadolibre.android.sdk.ApiResponse;
import com.mercadolibre.android.sdk.Identity;
import com.mercadolibre.android.sdk.Meli;
import com.smartmarket.data.question.Shipping;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

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
            QuestionManager questionManager = new QuestionManager(mIdentity);
            List<Shipping.Option> options = questionManager.getShippingOptions("MLB820966508", "13023240", "1");
            String answer = questionManager.buildShippingAnswer(getResources(), options);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            Identity identity = Meli.getCurrentIdentity(this);
        }
    }
}
