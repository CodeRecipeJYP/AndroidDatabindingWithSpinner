package com.asuscomm.yangyinetwork.boilerplate.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.asuscomm.yangyinetwork.boilerplate.domain.Company;
import com.asuscomm.yangyinetwork.boilerplate.network.CompanyService;
import com.asuscomm.yangyinetwork.boilerplate.network.RetrofitClients;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jaeyoung on 8/22/17.
 */

public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";

    @NonNull
    public ObservableField<String> mTitle = new ObservableField<>("initial");
    @NonNull
    public ObservableField<List<Company>> mCompanies = new ObservableField<List<Company>>(new ArrayList<>());

    public MainViewModel() {
        loadData();
    }

    private void loadData() {
        Log.d(TAG, "loadData: ");
        mTitle.set("initial");
        ArrayList<Company> companies = new ArrayList<>();
        companies.add(new Company("1", "initialCompany1"));
        companies.add(new Company("2", "initialCompany2"));
        mCompanies.set(companies);


        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Log.d(TAG, "loadData: run runnable");
            CompanyService service = RetrofitClients.getInstance().getService(CompanyService.class);
            service.getCompanies().enqueue(new Callback<List<Company>>() {
                @Override
                public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
                    Log.d(TAG, "onResponse: ");
                    if (response.isSuccessful()) {
                        mTitle.set(response.body().toString());
                        mCompanies.set(response.body());
                    } else {
                        Log.d(TAG, "onResponse: is not successful");
                    }
                }

                @Override
                public void onFailure(Call<List<Company>> call, Throwable t) {
                    Log.d(TAG, "onFailure: ");
                }
            });
        }, 3000);
    }
}
