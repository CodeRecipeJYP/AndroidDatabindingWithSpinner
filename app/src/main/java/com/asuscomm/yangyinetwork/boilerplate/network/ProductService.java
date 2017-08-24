package com.asuscomm.yangyinetwork.boilerplate.network;

import com.asuscomm.yangyinetwork.boilerplate.domain.Company;
import com.asuscomm.yangyinetwork.boilerplate.domain.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by jaeyoung on 24/08/2017.
 */

public interface ProductService {
    @GET("/api/products/{company_id}")
    Call<List<Product>> getProducts(@Path("company_id") String companyId);
}
