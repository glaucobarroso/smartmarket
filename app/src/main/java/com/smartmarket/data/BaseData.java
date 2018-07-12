package com.smartmarket.data;

import com.google.gson.Gson;

/**
 * Created by Glauco on 09/06/2018.
 */

public class BaseData {

    public static Object convertFromJson(String json, Class objClass) {
        Gson gson = new Gson();
        return gson.fromJson(json, objClass);
    }
}
