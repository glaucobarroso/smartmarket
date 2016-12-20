package com.smartmarket;

import com.mercadolibre.android.sdk.Identity;
import com.smartmarket.data.item.Item;

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

}
