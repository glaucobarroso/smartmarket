package com.smartmarket.manager;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.mercadolibre.android.sdk.Identity;
import com.smartmarket.data.message.PendingMessage;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Glauco on 27/05/2018.
 */

public class MessagesManager extends Manager {

    private final String PENDING_MESSAGES_FORMAT_STR = "/messages/pending_read?access_token=%s";

    public MessagesManager(Identity identity, Handler handler) {
        super(identity, handler);
    }

    private PendingMessage getPendingMessages() {
        String pendingMessagesJson = get(PENDING_MESSAGES_FORMAT_STR, mIdentity.getAccessToken().getAccessTokenValue());
        Gson gson = new Gson();
        return gson.fromJson(pendingMessagesJson, PendingMessage.class);
    }

    public class GetPendingMessages extends Thread {

        @Override
        public void run() {
            PendingMessage pendingMessages = getPendingMessages();
            Message msg = mHandler.obtainMessage();
            msg.what = GET_PENDING_MESSAGE;
            msg.obj = pendingMessages;
            mHandler.sendMessage(msg);
        }
    }

}
