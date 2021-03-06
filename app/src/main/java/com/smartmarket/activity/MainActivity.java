package com.smartmarket.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mercadolibre.android.sdk.Identity;
import com.mercadolibre.android.sdk.Meli;
import com.smartmarket.manager.ItemsManager;
import com.smartmarket.DefaultAdapter;
import com.smartmarket.manager.QuestionManager;
import com.smartmarket.R;
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

    private static final String TAG = "SmartMarket";
    private Handler mHandler;
    List<QuestionUIData> mQuestionsDataList;

    // Request code used to receive callbacks from the SDK
    public static final int REQUEST_CODE = 999;
    private final int MAX_WAIT_TIME = 2; // 2 minutes
    private ProgressDialog mProgress;
    private String mQuestionsLoading;
    private String mNoQuestions;
    private String mGetQuestionsFailed;
    private TextView mNoQuestionsView;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadUIMessages();
        mNoQuestionsView = (TextView) findViewById(R.id.noQuestions);
        mListView = (ListView) findViewById(R.id.list);
        mProgress = new ProgressDialog(this);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                mQuestionsDataList = (List<QuestionUIData>) msg.obj;
                if (mQuestionsDataList == null) {
                    TextView noQuestions = (TextView) findViewById(R.id.noQuestions);
                    mListView.setVisibility(View.GONE);
                    noQuestions.setVisibility(View.VISIBLE);
                    noQuestions.setText(mGetQuestionsFailed);
                    mProgress.dismiss();
                } else if (mQuestionsDataList.size() > 0) {
                    DefaultAdapter adapter = new DefaultAdapter(getApplicationContext(), R.layout.question_item, mQuestionsDataList);
                    mNoQuestionsView.setVisibility(View.GONE);
                    mListView.setVisibility(View.VISIBLE);
                    mListView.setAdapter(adapter);
                    mProgress.dismiss();
                } else {
                    TextView noQuestions = (TextView) findViewById(R.id.noQuestions);
                    mListView.setVisibility(View.GONE);
                    noQuestions.setVisibility(View.VISIBLE);
                    noQuestions.setText(mNoQuestions);
                    mProgress.dismiss();
                }
            }
        };
    }


    @Override
    public void onResume() {
        super.onResume();
        Meli.initializeSDK(this);
        if (!Meli.startLogin(this, REQUEST_CODE, Meli.AuthUrls.MLB)) {
            new RequestClass(Meli.getCurrentIdentity(this)).start();
        }
        showQuestionsLoadingDialog();
        ListView listView = (ListView) findViewById(R.id.list);
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
            Log.d(TAG, "Running...");
            QuestionManager questionManager = new QuestionManager(mIdentity, mHandler);
            final ItemsManager itemsManager = new ItemsManager(mIdentity, mHandler);
            final ConcurrentHashMap<String, QuestionUIData> uiDataHashMap = new ConcurrentHashMap<String, QuestionUIData>();
            List<Questions.Question> questions = questionManager.getQuestions();
            if (questions != null) {
                ExecutorService executorService = Executors.newCachedThreadPool();
                final List<QuestionUIData> questionsUiList = Collections.synchronizedList(new ArrayList<QuestionUIData>());
                for (final Questions.Question question : questions) {
                    if (QuestionManager.UNANSWERED.equals(question.getStatus())){
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
            } else {
                Message msg = mHandler.obtainMessage();
                msg.obj = null;
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
        mNoQuestions = resources.getString(R.string.no_questions);
        mGetQuestionsFailed = resources.getString(R.string.get_questions_failed);
    }

    private void showQuestionsLoadingDialog() {
        mProgress.setMessage(mQuestionsLoading);
        mProgress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        mProgress.show();
    }

    public void messagesClick(View v) {
        final Intent intent = new Intent(this, DefaultListActivity.class);
        startActivity(intent);
    }
}
