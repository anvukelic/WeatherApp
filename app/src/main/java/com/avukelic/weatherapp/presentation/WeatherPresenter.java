package com.avukelic.weatherapp.presentation;

import com.avukelic.weatherapp.interaction.ApiInteractor;
import com.avukelic.weatherapp.interaction.ApiInteractorImpl;
import com.avukelic.weatherapp.model.ForecastResponse;
import com.avukelic.weatherapp.model.WeatherResponse;
import com.avukelic.weatherapp.util.ImageHelper;
import com.avukelic.weatherapp.view.weather.WeatherContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by avukelic on 03-Oct-18.
 */
public class WeatherPresenter implements WeatherContract.Presenter {

    private final ApiInteractor apiInteractor;
    private WeatherContract.View weatherView;

    public WeatherPresenter() {
        apiInteractor = new ApiInteractorImpl();
    }

    @Override
    public void setView(WeatherContract.View view) {
        weatherView = view;
    }

    @Override
    public void getWeather(String location) {
        apiInteractor.getWeather(location, getWeatherCallback());
    }

    @Override
    public void getWeatherForecast(String location) {
        apiInteractor.getForecast(location, getForecastCallback());
    }

    @Override
    public void getWeatherByGps(double lon, double lat) {
        apiInteractor.getWeatherByGps(lon, lat, getWeatherCallback());
    }

    @Override
    public void getWeatherForecastByGps(double lon, double lat) {
        apiInteractor.getForecastByGps(lat, lon, getForecastCallback());
    }

    private Callback<ForecastResponse> getForecastCallback() {
        return new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ForecastResponse forecastResponse = response.body();
                    weatherView.showWeatherForecast(forecastResponse.getWeatherResponses());
                }
            }
            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                weatherView.showOnFailureError();
            }
        };
    }

    private Callback<WeatherResponse> getWeatherCallback() {
        return new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weatherResponse = response.body();
                    weatherView.showTime(weatherResponse.getTime());
                    weatherView.showTemperature(weatherResponse.getMain().getTemperature());
                    weatherView.showWindSpeed(weatherResponse.getWind().getSpeed());
                    weatherView.showPressure(weatherResponse.getMain().getPressure());
                    weatherView.showHumidity(weatherResponse.getMain().getHumidity());
                    weatherView.showDescription(weatherResponse.getWeatherObject().getDescription());
                    weatherView.showWeatherIcon(ImageHelper.createWeatherIconValue(weatherResponse.getWeatherObject().getMain()));
                    weatherView.showCity(weatherResponse.getCityName());
                    weatherView.hideProgressBar();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                weatherView.showOnFailureError();
            }
        };
    }


}
