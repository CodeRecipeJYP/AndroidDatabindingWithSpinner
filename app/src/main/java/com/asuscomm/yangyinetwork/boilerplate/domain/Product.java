package com.asuscomm.yangyinetwork.boilerplate.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaeyoung on 24/08/2017.
 */

public class Product {
    @SerializedName("_id")
    public String id;
    @SerializedName("product_name")
    public String productName;

    public Product(String id, String productName) {
        this.id = id;
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                '}';
    }
}
