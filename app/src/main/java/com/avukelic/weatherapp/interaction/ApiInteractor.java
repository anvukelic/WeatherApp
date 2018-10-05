package com.avukelic.weatherapp.interaction;

import com.avukelic.weatherapp.model.ForecastResponse;
import com.avukelic.weatherapp.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;

public interface ApiInteractor {

    void getWeather(String location, Callback<WeatherResponse> callback);

    void getForecast(String location, Callback<ForecastResponse> callback);

    void getWeatherByGps(double lat, double lon, Callback<WeatherResponse> callback);

    void getForecastByGps(double lat, double lon, Callback<ForecastResponse> callback);
}