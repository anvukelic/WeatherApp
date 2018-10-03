package com.avukelic.weatherapp.interaction;

import com.avukelic.weatherapp.App;
import com.avukelic.weatherapp.Consts;
import com.avukelic.weatherapp.model.WeatherResponse;

import retrofit2.Callback;

public class ApiInteractorImpl implements ApiInteractor {

    public ApiInteractorImpl() {
    }

    @Override
    public void checkLocationIfExists(String location, Callback<WeatherResponse> callback) {
        App.getApiService().getWeather(Consts.APP_ID, location, Consts.METRIC).enqueue(callback);
    }

    @Override
    public void getWeather(String location, Callback<WeatherResponse> callback) {
        App.getApiService().getWeather(Consts.APP_ID,location, Consts.METRIC).enqueue(callback);
    }
}