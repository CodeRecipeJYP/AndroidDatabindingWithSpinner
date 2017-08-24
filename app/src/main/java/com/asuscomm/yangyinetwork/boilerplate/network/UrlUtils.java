package com.asuscomm.yangyinetwork.boilerplate.network;

import java.util.HashMap;

/**
 * Created by jaeyoung on 24/08/2017.
 */

public class UrlUtils {
    private static HashMap<String, String> urls;

    static {
        urls = new HashMap<>();

        String apiUrl = "http://13.124.172.12:3000/";
        urls.put(CompanyService.class.getName(), apiUrl);
        urls.put(ProductService.class.getName(), apiUrl);
    }

    public static String getUrlWithClassName(String clsName) {
        return urls.get(clsName);
    }
}
