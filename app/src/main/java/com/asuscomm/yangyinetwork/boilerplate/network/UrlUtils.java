package com.asuscomm.yangyinetwork.boilerplate.network;

import java.util.HashMap;

/**
 * Created by jaeyoung on 24/08/2017.
 */

public class UrlUtils {
    private static HashMap<String, String> urls;

    static {
        urls = new HashMap<>();

//        String apiUrl = "http://52.79.142.130/";
//        urls.put(RoutineService.class.getName(), apiUrl);
    }

    public static String getUrlWithClassName(String clsName) {
        return urls.get(clsName);
    }
}
