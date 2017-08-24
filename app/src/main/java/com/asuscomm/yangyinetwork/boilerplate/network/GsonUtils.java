package com.asuscomm.yangyinetwork.boilerplate.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by jaeyoung on 24/08/2017.
 */

public class GsonUtils {
    private static Gson sGson;

    public static Gson getGson() {
        if (sGson == null) {
            sGson = new GsonBuilder()
                    .create();
        }

        return sGson;
    }
}
