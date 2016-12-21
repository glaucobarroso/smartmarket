package com.smartmarket;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.mercadolibre.android.sdk.Meli;
import com.smartmarket.data.question.Shipping;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Glauco on 20/12/2016.
 */

public class AnswerQuestionActivity extends AppCompatActivity {

    public static String QUESTION_TAG = "question";
    public static String ITEMID_TAG = "itemId";
    private static final String SHIPPING_PATTERN = "xfrete";
    private static final int TOTAL_STATES = SHIPPING_PATTERN.length();

    private EditText mAnswer;
    private int INIT_STATE = 0;
    private int mState;
    private String mQuestion;
    private String mItemId;
    private Pattern mShippingPattern;
    private Handler mHandler;
    private boolean mReplace = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);
        Intent intent = getIntent();
        mQuestion = intent.getStringExtra(QUESTION_TAG);
        mItemId = intent.getStringExtra(ITEMID_TAG);
        mShippingPattern = Pattern.compile(SHIPPING_PATTERN);

        TextView questionTextView = (TextView) findViewById(R.id.question);
        questionTextView.setText(mQuestion);
        mState = INIT_STATE;
        mAnswer = (EditText) findViewById(R.id.answer);
        mAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                Pattern pattern = Pattern.compile(SHIPPING_PATTERN);
                Matcher matcher = pattern.matcher(text);
                if (matcher.find() && !mReplace) {
                    addShippingMessage(mQuestion);
                    mReplace = true;
                }
            }
        });

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String text = (String) msg.obj;
                mAnswer.setText(text);
                mReplace = false;
            }
        };

    }

    private void addShippingMessage(String question) {
        final QuestionManager questionManager = new QuestionManager(Meli.getCurrentIdentity(this));
        final String cep = questionManager.getCep(question);
        if (cep != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // TODO remove hardcoded itemId
                    List < Shipping.Option > option = questionManager.getShippingOptions(mItemId, cep, "1");
                    if (option != null) {
                        String answer = questionManager.buildShippingAnswer(getResources(), option);
                        String currentText = mAnswer.getText().toString();
                        String newText = currentText.replace(SHIPPING_PATTERN, answer);
                        Message msg = mHandler.obtainMessage();
                        msg.obj = newText;
                        mHandler.sendMessage(msg);
                    }
                }
            }).start();
        }
    }
}
