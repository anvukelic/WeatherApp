package com.avukelic.weatherapp.view.weather;

import android.widget.Toast;

import com.avukelic.weatherapp.model.WeatherResponse;

import java.util.List;

/**
 * Created by avukelic on 03-Oct-18.
 */
public interface WeatherContract {
    interface View {
        void showTime(String time);

        void showTemperature(double temperature);

        void showWindSpeed(double windSpeed);

        void showPressure(double pressure);

        void showHumidity(int humidity);

        void showWeatherIcon(String path);

        void showDescription(String description);

        void showOnFailureError();

        void hideProgressBar();

        void showWeatherForecast(List<WeatherResponse> weatherResponses);

        void showCity(String cityName);
    }

    interface Presenter {
        void setView(WeatherContract.View view);

        void getWeather(String location);

        void getWeatherForecast(String location);

        void getWeatherByGps(double lon, double lat);

        void getWeatherForecastByGps(double lon, double lat);
    }
}
