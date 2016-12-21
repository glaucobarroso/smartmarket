package com.smartmarket;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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

    // Request code used to receive callbacks from the SDK
    public static final int REQUEST_CODE = 999;
    private Handler mHandler;
    List<QuestionUIData> mQuestionsDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Meli.initializeSDK(this);
        Meli.startLogin(this, REQUEST_CODE);
        new RequestClass(Meli.getCurrentIdentity(this)).start();
        ListView listView = (ListView) findViewById(R.id.activity_main);
        final Intent intent = new Intent(this, AnswerQuestionActivity.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView questionTextView = (TextView) view.findViewById(R.id.questionItemText);
                QuestionUIData uiData = mQuestionsDataList.get(i);
                String question = questionTextView.getText().toString();
                intent.putExtra(AnswerQuestionActivity.ITEMID_TAG, uiData.getItemId());
                intent.putExtra(AnswerQuestionActivity.QUESTION_TAG, questionTextView.getText().toString());
                startActivity(intent);

            }
        });

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                mQuestionsDataList = (List<QuestionUIData>) msg.obj;
                QuestionItemAdapter adapter = new QuestionItemAdapter(getApplicationContext(), R.layout.question_item, mQuestionsDataList);
                ListView listView = (ListView) findViewById(R.id.activity_main);
                listView.setAdapter(adapter);
            }
        };
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
            ExecutorService executorService = Executors.newCachedThreadPool();
            final List<QuestionUIData> questionsUiList = Collections.synchronizedList(new ArrayList<QuestionUIData>());
            for (final Questions.Question question : questions) {
                final String itemId = question.getItemId();
                QuestionUIData data = uiDataHashMap.get(itemId);
                if (data == null) {
                    executorService.execute(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Item item = itemsManager.getItem(question.getItemId());
                                                    String title = item.getTitle();
                                                    String text = question.getText();
                                                    QuestionUIData questionUIData = new QuestionUIData();
                                                    questionUIData.setItemTitle(title);
                                                    questionUIData.setQuestionText(text);
                                                    questionUIData.setItemId(itemId);
                                                    uiDataHashMap.put(question.getItemId(), questionUIData);
                                                    questionsUiList.add(questionUIData);
                                                }
                                            }
                    );
                } else {
                    QuestionUIData questionUIData = new QuestionUIData();
                    questionUIData.setQuestionText(question.getText());
                    questionUIData.setItemTitle(data.getItemTitle());
                    questionUIData.setItemId(itemId);
                    questionsUiList.add(questionUIData);
                }

            }
            executorService.shutdown();
            try {
                executorService.awaitTermination(3, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message msg = mHandler.obtainMessage();
            msg.obj = questionsUiList;
            mHandler.sendMessage(msg);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            Identity identity = Meli.getCurrentIdentity(this);
        }
    }
}
