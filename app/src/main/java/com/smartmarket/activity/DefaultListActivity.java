package com.smartmarket.activity;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.mercadolibre.android.sdk.Identity;
import com.mercadolibre.android.sdk.Meli;
import com.smartmarket.DefaultAdapter;
import com.smartmarket.data.item.Item;
import com.smartmarket.data.message.PendingMessage;
import com.smartmarket.data.order.Order;
import com.smartmarket.manager.ItemsManager;
import com.smartmarket.manager.Manager;
import com.smartmarket.manager.MessagesManager;
import com.smartmarket.R;
import com.smartmarket.manager.QuestionManager;
import com.smartmarket.manager.ShippingManager;
import com.smartmarket.ui.QuestionUIData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Glauco on 27/05/2018.
 */

public class DefaultListActivity extends AppCompatActivity {

    private static final String LOADING_MSG_TAG = "loading_msg";
    private static final String NO_DATA_MSG_TAG = "no_data_msg";
    private static final String FAILED_MSG_TAG = "failed_msg";
    private static final int ITEMS_TRIGGER_LIMIT = 2;
    private ProgressDialog mProgress;
    private String mNoData;
    private String mGetListFailed;
    private String mLoading;
    private TextView mNoDataView;
    private ListView mListView;
    private MessagesManager mMessagesManager;
    private ItemsManager mItemsManager;
    private Manager mManager;
    private ShippingManager mShippingManager;
    private Identity mIdentity;
    private Handler mHandler;
    public final static int GET_PENDING_MESSAGE = 0;
    public final static int GET_ORDERS = 1;
    public final static int GET_ITEMS = 2;
    public final static int GET_THUMBNAILS = 3;
    public final static int GET_MESSAGES = 4;
    List<String> mItemList = new ArrayList<>();
    private List<Order> mOrders;
    private List<Item> mItems;
    private List<Bitmap> mThumbnails;
    private List<QuestionUIData> mUiData;
    private int mGetItemsTrigger = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_list);
        loadUIMessages();
        mNoDataView = (TextView) findViewById(R.id.noData);
        mListView = (ListView) findViewById(R.id.list);
        mProgress = new ProgressDialog(this);
        mHandler = new DefaultListHandler();
    }

    @Override
    public void onResume() {
        super.onResume();
        Meli.initializeSDK(this);
        if (!Meli.startLogin(this, MainActivity.REQUEST_CODE, Meli.AuthUrls.MLB)) {
            mIdentity = Meli.getCurrentIdentity(this);
//            mShippingManager = new ShippingManager(mIdentity, mHandler);
            mManager = new Manager(mIdentity, mHandler);
            mMessagesManager = new MessagesManager(mIdentity, mHandler);
            mItemsManager = new ItemsManager(mIdentity, mHandler);
//            mOrdersManager = new OrdersManager(mIdentity, mHandler);
            mMessagesManager. new GetPendingMessages().start();
        }
        showLoadingDialog();

        /*ListView listView = (ListView) findViewById(R.id.list);
        final Intent intent = new Intent(this, AnswerQuestionActivity.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                QuestionUIData uiData = mQuestionsDataList.get(i);
                intent.putExtra(AnswerQuestionActivity.DATA_TAG, uiData);
                startActivity(intent);
            }
        });*/
    }

    private void loadUIMessages() {
        Resources resources = getResources();
        mLoading = resources.getString(R.string.loading_msg);
        mNoData = resources.getString(R.string.no_data_msg);
        mGetListFailed = resources.getString(R.string.failed_msg);
    }

    private void showLoadingDialog() {
        mProgress.setMessage(mLoading);
        mProgress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        mProgress.show();
    }

    class DefaultListHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            if (msg.obj == null) {
                mListView.setVisibility(View.GONE);
                mNoDataView.setVisibility(View.VISIBLE);
                mNoDataView.setText(mNoData);
                mProgress.dismiss();
            }
            else {
                switch (msg.what) {
                case GET_PENDING_MESSAGE:
                    PendingMessage pendingMessage = (PendingMessage) msg.obj;
                    List<String> ordersList = new ArrayList<String>();
                    mUiData = new ArrayList<>();
                    if (pendingMessage.results != null && pendingMessage.results.size() > 0) {
                        for (PendingMessage.Result result : pendingMessage.results) {
                            ordersList.add(result.order_id);
                        }
                    }
                    mManager.new GetList(Manager.GET_ORDER_FORMAT_STR, ordersList, GET_ORDERS, true).start();
                    mManager.new GetList(Manager.GET_MESSAGE_FORMAT_STR, ordersList, GET_MESSAGES, true).start();

                    break;
                case GET_ORDERS:
                    mGetItemsTrigger++;
                    ArrayList<String> ordersJsonList = msg.getData().getStringArrayList(Manager.JSON_LIST);
                    mOrders = new ArrayList<>();
                    for (String json : ordersJsonList) {
                        mOrders.add((Order) Order.convertFromJson(json, Order.class));
                    }
                    mItemList = new ArrayList<>();
                    for (Order order : mOrders) {
                        Order.OrderItem orderItem = order.orderItems.get(0); // for simplicity only get info from the first item of the order
                        if (orderItem != null && orderItem.item != null) {
                            mItemList.add(orderItem.item.id);
                        }
                    }
                    if (mGetItemsTrigger == ITEMS_TRIGGER_LIMIT) {
                        mManager.new GetList(Manager.GET_ITEM_FORMAT_STR, mItemList, GET_ITEMS, false).start();
                        mGetItemsTrigger = 0;
                    }
                    break;
                case GET_MESSAGES:
                    mGetItemsTrigger++;
                    ArrayList<String> messagesJsonList = msg.getData().getStringArrayList(Manager.JSON_LIST);
                    if (mGetItemsTrigger == ITEMS_TRIGGER_LIMIT) {
                        mManager.new GetList(Manager.GET_ITEM_FORMAT_STR, mItemList, GET_ITEMS, false);
                        mGetItemsTrigger = 0;
                    }
                    break;
                case GET_ITEMS:
                    ArrayList<String> itemsJsonList = msg.getData().getStringArrayList(Manager.JSON_LIST);
                    mItems = new ArrayList<>();
                    for (String json : itemsJsonList) {
                        mItems.add((Item) Item.convertFromJson(json, Order.class));
                    }
                    mItemsManager.new GetThumbnails(mItems, GET_THUMBNAILS).start();
                    break;
                case GET_THUMBNAILS:
                    mThumbnails = (List<Bitmap>) msg.obj;
                    List<QuestionUIData> uidata = new ArrayList<>();
                    for (int i = 0; i < mItems.size(); i++) {
                        Item item = mItems.get(i);
                        Bitmap bitmap = mThumbnails.get(i);
//                        uidata.add(QuestionManager.fillQuestionUIData(item.getId(), ))

                    }
                    break;
                }
            }
        }
    }
}
