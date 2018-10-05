package com.avukelic.weatherapp;

import android.app.Application;

import com.avukelic.weatherapp.network.ApiService;
import com.avukelic.weatherapp.network.RetrofitUtil;

import retrofit2.Retrofit;

/**
 * Created by avukelic on 02-Oct-18.
 */
public class App extends Application {
    private static Retrofit retrofit;
    private static ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = RetrofitUtil.createRetrofit();
        apiService = retrofit.create(ApiService.class);
    }

    public static ApiService getApiService() {
        return apiService;
    }
}
