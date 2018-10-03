package com.avukelic.weatherapp.view.weather;

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

        void showOnNetworkError(int message);

        void showOnFailurError();
    }

    interface Presenter {
        void setView(WeatherContract.View view);

        void getWeather(String location);
    }
}
