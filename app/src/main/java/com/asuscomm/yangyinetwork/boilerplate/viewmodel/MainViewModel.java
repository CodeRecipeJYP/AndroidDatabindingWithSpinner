package com.asuscomm.yangyinetwork.boilerplate.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.asuscomm.yangyinetwork.boilerplate.domain.Company;
import com.asuscomm.yangyinetwork.boilerplate.domain.Product;
import com.asuscomm.yangyinetwork.boilerplate.network.CompanyService;
import com.asuscomm.yangyinetwork.boilerplate.network.ProductService;
import com.asuscomm.yangyinetwork.boilerplate.network.RetrofitClients;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";

    @NonNull
    public ObservableField<String> mTitle = new ObservableField<>("initial");
    @NonNull
    public ObservableField<Boolean> mTitleProgress = new ObservableField<>(false);
    @NonNull
    public ObservableField<List<Company>> mCompanies = new ObservableField<>(new ArrayList<>());
    @NonNull
    public ObservableField<Boolean> mCompaniesProgress = new ObservableField<>(false);

    @NonNull
    public ObservableField<Integer> mSelectedCompanyPosition = new ObservableField<>();

    public MainViewModel() {
        loadData();
        mSelectedCompanyPosition.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
//                Log.d(TAG, "onPropertyChanged() called with: " +
//                        "mSelectedCompanyPosition = [" + mSelectedCompanyPosition.get() + "]");
                List<Company> companies = mCompanies.get();
                Company company = companies.get(mSelectedCompanyPosition.get());
                onItemSelected(company);
            }
        });
    }

    private void onItemSelected(@NonNull Company company) {
        Log.d(TAG, "onItemSelected() called with: company = [" + company + "]");
        ProductService service = RetrofitClients.getInstance().getService(ProductService.class);
        service.getProducts(company.id).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> products = response.body();
                    Log.d(TAG, "onResponse() called with: " +
                            "products = [" + products.toString() + "]");
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    private void loadData() {
        Log.d(TAG, "loadData: ");
        mTitle.set("initial");
//        ArrayList<Company> companies = new ArrayList<>();
//        companies.add(new Company("1", "initialCompany1"));
//        companies.add(new Company("2", "initialCompany2"));
//        mCompanies.set(companies);

        mCompaniesProgress.set(true);
        mTitleProgress.set(true);

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
                        mCompaniesProgress.set(false);
                        mTitleProgress.set(false);
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
