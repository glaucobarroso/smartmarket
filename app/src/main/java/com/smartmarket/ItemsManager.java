package com.smartmarket;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mercadolibre.android.sdk.Identity;
import com.smartmarket.data.item.Item;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Glauco on 19/12/2016.
 */

public class ItemsManager extends Manager {

    private final String GET_ITEM_FORMAT_STR = "/items/%s";

    public ItemsManager(Identity identity) {
        super(identity);
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

}
