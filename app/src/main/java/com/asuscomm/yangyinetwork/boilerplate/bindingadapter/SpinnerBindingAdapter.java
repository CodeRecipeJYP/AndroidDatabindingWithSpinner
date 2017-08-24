package com.asuscomm.yangyinetwork.boilerplate.bindingadapter;

import android.databinding.BindingAdapter;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.List;

/**
 * Created by jaeyoung on 24/08/2017.
 */

public class SpinnerBindingAdapter {
    private static final String TAG = "SpinnerBindingAdapter";

    @BindingAdapter("items")
    public static <T> void setItems(@NonNull Spinner spinner, @NonNull List<T> items) {
        SpinnerAdapter spinnerAdapter = spinner.getAdapter();
        if (spinnerAdapter instanceof ArrayAdapter) {
            Log.d(TAG, "setItems: ");
            ArrayAdapter arrayAdapter = (ArrayAdapter) spinnerAdapter;
            arrayAdapter.clear();
            arrayAdapter.addAll(items);
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
