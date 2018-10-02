package com.avukelic.weatherapp;

import android.app.Application;

import com.avukelic.weatherapp.network.ApiService;
import com.avukelic.weatherapp.network.RetrofitUtil;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Retrofit;

/**
 * Created by avukelic on 02-Oct-18.
 */
public class App extends Application {
    private static Retrofit retrofit;
    private static ApiService apiService;
    private static Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = RetrofitUtil.createRetrofit();
        apiService = retrofit.create(ApiService.class);
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("weatherapp.realm")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfig);

        realm = Realm.getDefaultInstance();
    }

    public static Realm getRealm() {
        return realm;
    }


    public static ApiService getApiService() {
        return apiService;
    }
}
