package com.asuscomm.yangyinetwork.boilerplate.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.asuscomm.yangyinetwork.boilerplate.domain.Company;
import com.asuscomm.yangyinetwork.boilerplate.network.CompanyService;
import com.asuscomm.yangyinetwork.boilerplate.network.RetrofitClients;

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

    public MainViewModel() {
        loadData();
    }

    private void loadData() {
        CompanyService service = RetrofitClients.getInstance().getService(CompanyService.class);
        service.getCompanies().enqueue(new Callback<List<Company>>() {
            @Override
            public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
                Log.d(TAG, "onResponse: ");
                if (response.isSuccessful()) {
                    mTitle.set(response.body().toString());
                } else {
                    Log.d(TAG, "onResponse: is not successful");
                }
            }

            @Override
            public void onFailure(Call<List<Company>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
}
