package com.smartmarket.manager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.mercadolibre.android.sdk.ApiResponse;
import com.mercadolibre.android.sdk.Identity;
import com.mercadolibre.android.sdk.Meli;
import com.smartmarket.data.message.PendingMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Glauco on 19/12/2016.
 */

public class Manager {

    protected Gson mGson;
    protected Identity mIdentity;
    protected Handler mHandler;
    public static final String JSON_LIST = "list";
    public static final String GET_ORDER_FORMAT_STR = "/orders/%s?access_token=%s";
    public static final String GET_ITEM_FORMAT_STR = "/items/%s";
    public static final String GET_MESSAGE_FORMAT_STR = "/messages/orders/%s?access_token=%s";

    public Manager(Identity identity, Handler handler) {
        mGson = new Gson();
        mIdentity = identity;
        mHandler = handler;
    }

    public String get(String formatString, String ... args) {
        String path = String.format(formatString, args);
        ApiResponse response = Meli.get(path);
        if (response.getResponseCode() == ApiResponse.ApiResponseCode.RESPONSE_CODE_SUCCESS) {
            return response.getContent();
        }
        return null;
    }

    protected void sendMessage(int what, ArrayList<String> jsonList) {
        Message msg = mHandler.obtainMessage();
        msg.what = what;
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(JSON_LIST, jsonList);
        msg.setData(bundle);
        mHandler.sendMessage(msg);
    }

    public ArrayList<String> getList(List<String> list, String formatString, boolean requireAccessToken) {
        ArrayList<String> ret = new ArrayList<>();
        for (String item : list) {
            if (requireAccessToken) {
                ret.add(get(formatString, item, mIdentity.getAccessToken().getAccessTokenValue()));
            } else {
                ret.add(get(formatString, item));
            }
        }
        return ret;
    }

    public class GetList extends Thread {

        private List<String> mList;
        private int mWhat;
        private boolean mRequireAccessToken;
        private String mFormatStr;

        public GetList(String formatString, List<String> list, int what, boolean requireAccessToken) {
            mList = list;
            mWhat = what;
            mRequireAccessToken = requireAccessToken;
            mFormatStr = formatString;
        }

        @Override
        public void run() {
            ArrayList<String> jsonList = getList(mList, mFormatStr, mRequireAccessToken);
            sendMessage(mWhat, jsonList);
        }
    }
}
