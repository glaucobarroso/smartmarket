package com.smartmarket;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mercadolibre.android.sdk.ApiResponse;
import com.mercadolibre.android.sdk.Meli;
import com.smartmarket.data.question.ChatMessage;
import com.smartmarket.data.question.Questions;
import com.smartmarket.data.question.Shipping;
import com.smartmarket.ui.QuestionUIData;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Glauco on 20/12/2016.
 */

public class AnswerQuestionActivity extends AppCompatActivity implements View.OnClickListener{

    public static String DATA_TAG = "data";
    private static final String SHIPPING_PATTERN = "xfrete";
    private static final int SHIPPING_TEXT = 0;
    private static final int ANSWER_SENT = 1;
    private static final int CHAT_LOADED = 2;

    private EditText mAnswer;
    private QuestionUIData mData;
    private Pattern mShippingPattern;
    private Handler mHandler;
    private boolean mReplace = false;
    private Button mSend;
    private Resources mResources;
    private ProgressDialog mProgress;
    private ListView mChat;

    private String mAnswerEmptyMsg;
    private String mAnswerSuccessMsg;
    private String mAnswerFailMsg;
    private String mAnswerSendingMsg;
    private String mLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);
        mResources = getResources();
        loadUIMessages();
        Intent intent = getIntent();
        mData = intent.getParcelableExtra(DATA_TAG);
        mShippingPattern = Pattern.compile(SHIPPING_PATTERN);
        mProgress = new ProgressDialog(this);

        mChat = (ListView) findViewById(R.id.chat);
        mSend = (Button) findViewById(R.id.answer_button);
        mSend.setOnClickListener(this);
        mAnswer = (EditText) findViewById(R.id.answer_text);
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
                    addShippingMessage(mData.getQuestion());
                    mReplace = true;
                }
            }
        });

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch(msg.what) {
                    case SHIPPING_TEXT:
                        String text = (String) msg.obj;
                        mAnswer.setText(text);
                        mReplace = false;
                        break;
                    case ANSWER_SENT:
                        mProgress.dismiss();
                        if (msg.arg1 == ApiResponse.ApiResponseCode.RESPONSE_CODE_SUCCESS) {
                            finish();
                        } else {
                            showDialog(mAnswerFailMsg, true);
                        }
                        break;
                    case CHAT_LOADED:
                        List<ChatMessage> messages = (List<ChatMessage>) msg.obj;
                        ChatItemAdapter adapter = new ChatItemAdapter(getApplicationContext(), R.layout.chat_item, messages);
                        mChat.setAdapter(adapter);
                        mProgress.dismiss();
                        break;
                }
            }
        };

        showProgressBar(mLoading);
        loadChat(mData.getItemId(), mData.getFromId());
    }

    private void addShippingMessage(String question) {
        final QuestionManager questionManager = new QuestionManager(Meli.getCurrentIdentity(this));
        final String cep = questionManager.getCep(question);
        if (cep != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // TODO remove hardcoded itemId
                    List < Shipping.Option > option = questionManager.getShippingOptions(mData.getItemId(), cep, "1");
                    if (option != null) {
                        String answer = questionManager.buildShippingAnswer(mResources, option);
                        String currentText = mAnswer.getText().toString();
                        String newText = currentText.replace(SHIPPING_PATTERN, answer);
                        Message msg = mHandler.obtainMessage();
                        msg.obj = newText;
                        msg.what = SHIPPING_TEXT;
                        mHandler.sendMessage(msg);
                    }
                }
            }).start();
        }
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.answer_button:
                final QuestionManager questionManager = new QuestionManager(Meli.getCurrentIdentity(this));
                final String answerText;
                showAnswerSendingDialog();
                try {
                    answerText = new String(mAnswer.getEditableText().toString().getBytes(), "UTF-8");
                    if (answerText == null || answerText.length() == 0) {
                        showDialog(mAnswerEmptyMsg, false);
                        mProgress.dismiss();
                    } else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                int response = questionManager.answerQuestion(mData.getQuestionId(), answerText);
                                Message message = mHandler.obtainMessage();
                                message.what = ANSWER_SENT;
                                message.arg1 = response;
                                mHandler.sendMessage(message);
                            }
                        }
                        ).start();
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void loadUIMessages() {
        mAnswerEmptyMsg = mResources.getString(R.string.empty_answer_message);
        mAnswerFailMsg = mResources.getString(R.string.answer_fail);
        mAnswerSuccessMsg = mResources.getString(R.string.answer_success);
        mAnswerSendingMsg = mResources.getString(R.string.answer_sending);
        mLoading = mResources.getString(R.string.loading);
    }

    private void showDialog(String message, final boolean finish) {
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage(message);
        dlgAlert.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (finish)
                            finish();
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    private void showAnswerSendingDialog() {
        showProgressBar(mAnswerSendingMsg);
    }

    private void showProgressBar(String message) {
        mProgress.setMessage(message);
        mProgress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        mProgress.show();
    }

    private void loadChat(final String itemId, final Integer buyerId) {
        final QuestionManager questionManager = new QuestionManager(Meli.getCurrentIdentity(this));
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Questions.Question> questions = questionManager.getQuestionsByUser(itemId, buyerId);
                List<ChatMessage> messages = new ArrayList<ChatMessage>();
                if (questions != null) {
                    for (Questions.Question question : questions) {
                        ChatMessage chatQuestion = new ChatMessage();
                        String questionText = question.getText();
                        chatQuestion.setMessage(questionText);
                        chatQuestion.setType(ChatMessage.BUYER_MESSAGE);
                        messages.add(chatQuestion);
                        if (question.getAnswer() != null) {
                            ChatMessage chatAnswer = new ChatMessage();
                            chatAnswer.setMessage(question.getAnswer().getText());
                            chatAnswer.setType(ChatMessage.MY_MESSAGE);
                            messages.add(chatAnswer);
                        }
                    }
                }
                Message msg = mHandler.obtainMessage();
                msg.what = CHAT_LOADED;
                msg.obj = messages;
                mHandler.sendMessage(msg);
            }
        }).start();
    }
}
