package com.smartmarket.manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.mercadolibre.android.sdk.Identity;
import com.smartmarket.data.item.Item;
import com.smartmarket.data.message.PendingMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Glauco on 19/12/2016.
 */

public class ItemsManager extends Manager {

    public ItemsManager(Identity identity, Handler handler) {
        super(identity, handler);
    }

    public Item getItem(String itemId) {
        String content = get(GET_ITEM_FORMAT_STR, itemId);
        if (content != null) {
            Item item = mGson.fromJson(content, Item.class);
            return item;
        }
        return null;
    }

    public Bitmap getItemThumbnail(Item item) {
        Bitmap thumbnail = null;
        try {
            if (item.getSecureThumbnail() != null) {
                thumbnail = BitmapFactory.decodeStream((InputStream) new URL(item.getSecureThumbnail()).getContent());
            } else if (item.getThumbnail() != null) {
                thumbnail = BitmapFactory.decodeStream((InputStream) new URL(item.getThumbnail()).getContent());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return thumbnail;
    }

    public List<Bitmap> getItemsThumbnails(List<Item> list) {
        List<Bitmap> ret = new ArrayList<Bitmap>();
        for (Item item : list) {
            ret.add(getItemThumbnail(item));
        }
        return ret;
    }

    public class GetThumbnails extends Thread {

        private List<Item> mList;
        private int mWhat;

        public GetThumbnails(List<Item> list, int what) {
            mList = list;
            mWhat = what;
        }

        private void sendThumbnailMessage(List<Bitmap> bitmaps) {
            Message msg = mHandler.obtainMessage();
            msg.what = mWhat;
            msg.obj = bitmaps;
            mHandler.sendMessage(msg);
        }

        @Override
        public void run() {
            sendThumbnailMessage(getItemsThumbnails(mList));
        }
    }

}
