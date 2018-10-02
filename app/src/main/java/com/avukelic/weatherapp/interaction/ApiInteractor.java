package com.avukelic.weatherapp.interaction;

import com.avukelic.weatherapp.model.WeatherResponse;

import retrofit2.Callback;

public interface ApiInteractor {

    void checkLocationIfExists(String location, Callback<WeatherResponse> callback);

    void getWeather(String location, Callback<WeatherResponse> callback);
}