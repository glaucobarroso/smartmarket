package com.smartmarket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mercadolibre.android.sdk.Identity;
import com.mercadolibre.android.sdk.Meli;
import com.smartmarket.data.item.Item;
import com.smartmarket.data.question.Questions;
import com.smartmarket.ui.QuestionUIData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler;
    List<QuestionUIData> mQuestionsDataList;

    // Request code used to receive callbacks from the SDK
    public static final int REQUEST_CODE = 999;
    private final int MAX_WAIT_TIME = 2; // 2 minutes
    private final String UNANSWERED = "UNANSWERED";
    private ProgressDialog mProgress;
    private String mQuestionsLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadUIMessages();
        mProgress = new ProgressDialog(this);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                mQuestionsDataList = (List<QuestionUIData>) msg.obj;
                QuestionItemAdapter adapter = new QuestionItemAdapter(getApplicationContext(), R.layout.question_item, mQuestionsDataList);
                ListView listView = (ListView) findViewById(R.id.activity_main);
                listView.setAdapter(adapter);
                mProgress.dismiss();
            }
        };
    }


    @Override
    public void onResume() {
        super.onResume();
        Meli.initializeSDK(this);
        if (!Meli.startLogin(this, REQUEST_CODE)) {
            new RequestClass(Meli.getCurrentIdentity(this)).start();
        }
        showQuestionsLoadingDialog();
        ListView listView = (ListView) findViewById(R.id.activity_main);
        final Intent intent = new Intent(this, AnswerQuestionActivity.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                QuestionUIData uiData = mQuestionsDataList.get(i);
                intent.putExtra(AnswerQuestionActivity.DATA_TAG, uiData);
                startActivity(intent);
            }
        });
    }

    class RequestClass extends Thread {

        Identity mIdentity;

        RequestClass(Identity identity) {
            mIdentity = identity;
        }

        @Override
        public void run() {
            QuestionManager questionManager = new QuestionManager(mIdentity);
            final ItemsManager itemsManager = new ItemsManager(mIdentity);
            final ConcurrentHashMap<String, QuestionUIData> uiDataHashMap = new ConcurrentHashMap<String, QuestionUIData>();
            List<Questions.Question> questions = questionManager.getQuestions();
            if (questions != null) {
                ExecutorService executorService = Executors.newCachedThreadPool();
                final List<QuestionUIData> questionsUiList = Collections.synchronizedList(new ArrayList<QuestionUIData>());
                for (final Questions.Question question : questions) {
                    if (UNANSWERED.equals(question.getStatus())){
                        QuestionUIData data = uiDataHashMap.get(question.getItemId());
                        if (data == null) {
                            executorService.execute(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Item item = itemsManager.getItem(question.getItemId());
                                                            Bitmap thumbnail = itemsManager.getItemThumbnail(item);
                                                            QuestionUIData questionUIData = fillQuestionUIData(question.getItemId(), item.getTitle(),
                                                                    question.getText(), question.getId(), thumbnail, question.getFrom().getId());
                                                            uiDataHashMap.put(question.getItemId(), questionUIData);
                                                            questionsUiList.add(questionUIData);
                                                        }
                                                    }
                            );
                        } else {
                            QuestionUIData questionUIData = fillQuestionUIData(question.getItemId(), data.getItemTitle(), question.getText(),
                                    question.getId(), data.getThumbnail(), question.getFrom().getId());
                            questionsUiList.add(data);
                        }
                    }
                }
                executorService.shutdown();
                try {
                    executorService.awaitTermination(MAX_WAIT_TIME, TimeUnit.MINUTES);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = mHandler.obtainMessage();
                msg.obj = questionsUiList;
                mHandler.sendMessage(msg);
            }
        }
    }

    private QuestionUIData fillQuestionUIData(String itemId, String itemTitle, String questionText, Long questionId, Bitmap thumbnail, Integer from) {
        QuestionUIData questionUIData = new QuestionUIData();
        questionUIData.setQuestionText(questionText);
        questionUIData.setItemTitle(itemTitle);
        questionUIData.setItemId(itemId);
        questionUIData.setQuestionId(questionId);
        questionUIData.setThumbnail(thumbnail);
        questionUIData.setFromId(from);
        return questionUIData;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            Identity identity = Meli.getCurrentIdentity(this);
            new RequestClass(Meli.getCurrentIdentity(this)).start();
        }
    }

    private void loadUIMessages() {
        Resources resources = getResources();
        mQuestionsLoading = resources.getString(R.string.questions_loading);
    }

    private void showQuestionsLoadingDialog() {
        mProgress.setMessage(mQuestionsLoading);
        mProgress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        mProgress.show();
    }
}
