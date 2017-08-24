package com.asuscomm.yangyinetwork.boilerplate.network;

import com.asuscomm.yangyinetwork.boilerplate.domain.Company;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jaeyoung on 24/08/2017.
 */

public interface CompanyService {
    @GET("/api/companies/")
    Call<List<Company>> getCompanies();
}
