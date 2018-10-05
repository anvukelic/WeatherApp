package com.avukelic.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by avukelic on 04-Oct-18.
 */
public class ForecastResponse {
    @Expose
    @SerializedName("list")
    private List<WeatherResponse> weatherResponses;


    public ForecastResponse() {
    }

    public List<WeatherResponse> getWeatherResponses() {
        return weatherResponses;
    }

    public void setWeatherResponses(List<WeatherResponse> weatherResponses) {
        this.weatherResponses = weatherResponses;
    }
}
