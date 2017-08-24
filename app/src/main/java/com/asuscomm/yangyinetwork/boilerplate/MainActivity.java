package com.asuscomm.yangyinetwork.boilerplate;

import android.arch.lifecycle.ViewModelProviders;
import android.database.DataSetObserver;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import com.asuscomm.yangyinetwork.boilerplate.databinding.ActivityMainBinding;
import com.asuscomm.yangyinetwork.boilerplate.domain.Company;
import com.asuscomm.yangyinetwork.boilerplate.network.CompanyService;
import com.asuscomm.yangyinetwork.boilerplate.network.RetrofitClients;
import com.asuscomm.yangyinetwork.boilerplate.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MainViewModel mViewModel;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        initView();
    }

    private void initView() {
        mBinding.spinnerCompanies.setAdapter(
                new ArrayAdapter<Company>(
                        this,
                        R.layout.main_spinner_companyitem,
                        new ArrayList<Company>()
                ));
    }

    private void initViewModel() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mBinding.setMainviewmodel(mViewModel);
        mViewModel.attachViews(mBinding);
    }
}
