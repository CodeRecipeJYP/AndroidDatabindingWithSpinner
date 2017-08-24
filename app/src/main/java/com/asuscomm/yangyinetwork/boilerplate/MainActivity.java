package com.asuscomm.yangyinetwork.boilerplate;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.asuscomm.yangyinetwork.boilerplate.databinding.ActivityMainBinding;
import com.asuscomm.yangyinetwork.boilerplate.domain.Company;
import com.asuscomm.yangyinetwork.boilerplate.network.CompanyService;
import com.asuscomm.yangyinetwork.boilerplate.network.RetrofitClients;
import com.asuscomm.yangyinetwork.boilerplate.viewmodel.MainViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding
                = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setMainviewmodel(mViewModel);

        CompanyService service = RetrofitClients.getInstance().getService(CompanyService.class);
        service.getCompanies().enqueue(new Callback<List<Company>>() {
            @Override
            public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<List<Company>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
}
