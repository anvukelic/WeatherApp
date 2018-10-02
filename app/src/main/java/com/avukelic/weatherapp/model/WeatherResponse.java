package com.avukelic.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

    @Expose
    @SerializedName("weather")
    private Weather[] weather = new Weather[1];
    @Expose
    @SerializedName("main")
    private Main main;
    @Expose
    @SerializedName("wind")
    private Wind wind;
    @Expose
    @SerializedName("name")
    private String cityName;

    public WeatherResponse(Weather[] weather, Main main, Wind wind, String dt_txt, String cityName) {
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.cityName = cityName;
    }

    public WeatherResponse() {
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Weather getWeatherObject() {
        return weather[0];
    }

    public void setWeatherObject(Weather weatherObject) {
        this.weather[0] = weatherObject;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}