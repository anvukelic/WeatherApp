package com.avukelic.weatherapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @Expose
    @SerializedName("temp")
    private double temperature;
    @Expose
    @SerializedName("temp_min")
    private double temperatureMin;
    @Expose
    @SerializedName("temp_max")
    private double temperatureMax;
    @Expose
    @SerializedName("humidity")
    private int humidity;
    @Expose
    @SerializedName("pressure")
    private double pressure;

    public Main(double temperature, double temperature_min, double temperature_max, int humidity, double pressure) {
        this.temperature = temperature;
        this.temperatureMin = temperature_min;
        this.temperatureMax = temperature_max;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature_min() {
        return temperatureMin;
    }

    public void setTemperature_min(double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public double getTemperature_max() {
        return temperatureMax;
    }

    public void setTemperature_max(double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }
}