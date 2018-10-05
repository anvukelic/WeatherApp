package com.avukelic.weatherapp.interaction;

import com.avukelic.weatherapp.App;
import com.avukelic.weatherapp.Consts;
import com.avukelic.weatherapp.model.ForecastResponse;
import com.avukelic.weatherapp.model.WeatherResponse;

import retrofit2.Callback;

public class ApiInteractorImpl implements ApiInteractor {

    public ApiInteractorImpl() {
    }

    @Override
    public void getWeather(String location, Callback<WeatherResponse> callback) {
        App.getApiService().getWeather(Consts.APP_ID,location, Consts.METRIC).enqueue(callback);
    }
    @Override
    public void getForecast(String location, Callback<ForecastResponse> callback) {
        App.getApiService().getForecast(Consts.APP_ID,location, Consts.METRIC).enqueue(callback);
    }

    @Override
    public void getWeatherByGps(double lat, double lon, Callback<WeatherResponse> callback) {
        App.getApiService().getWeatherWithGps(Consts.APP_ID, lat, lon, Consts.METRIC).enqueue(callback);
    }

    @Override
    public void getForecastByGps(double lat, double lon, Callback<ForecastResponse> callback) {
        App.getApiService().getForecastWithGps(Consts.APP_ID, lat, lon, Consts.METRIC).enqueue(callback);
    }
}