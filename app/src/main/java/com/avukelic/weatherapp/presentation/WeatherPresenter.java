package com.avukelic.weatherapp.presentation;

import com.avukelic.weatherapp.Consts;
import com.avukelic.weatherapp.R;
import com.avukelic.weatherapp.interaction.ApiInteractor;
import com.avukelic.weatherapp.interaction.ApiInteractorImpl;
import com.avukelic.weatherapp.model.Weather;
import com.avukelic.weatherapp.model.WeatherResponse;
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
        apiInteractor.getWeather(location, getWeatherCallback(location));
    }

    private Callback<WeatherResponse> getWeatherCallback(final String location) {
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
                    weatherView.showWeatherIcon(createWeatherIconValue(weatherResponse.getWeatherObject().getMain()));
                } else {
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                weatherView.showOnFailurError();
                System.out.println(t);
            }
        };
    }

    private String createWeatherIconValue(String description) {
        if (description != null)
            switch (description) {
                case Consts.SNOW_CASE: {
                    return Consts.SNOW;
                }
                case Consts.RAIN_CASE: {
                    return Consts.RAIN;
                }
                case Consts.CLEAR_CASE: {
                    return Consts.SUN;
                }
                case Consts.MIST_CASE: {
                    return Consts.FOG;
                }
                case Consts.FOG_CASE: {
                    return Consts.FOG;
                }
                case Consts.HAZE_CASE: {
                    return Consts.FOG;
                }
                case Consts.CLOUD_CASE: {
                    return Consts.CLOUD;
                }
            }
        return "";
    }
}
