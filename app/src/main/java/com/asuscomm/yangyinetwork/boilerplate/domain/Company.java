package com.asuscomm.yangyinetwork.boilerplate.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaeyoung on 24/08/2017.
 */

public class Company {
    @SerializedName("_id")
    public String id;
    @SerializedName("company_name")
    public String companyName;
}
