package com.avukelic.weatherapp.presentation;

import com.avukelic.weatherapp.interaction.ApiInteractor;
import com.avukelic.weatherapp.interaction.ApiInteractorImpl;
import com.avukelic.weatherapp.interaction.DbInteractor;
import com.avukelic.weatherapp.interaction.DbInteractorImpl;
import com.avukelic.weatherapp.model.WeatherResponse;
import com.avukelic.weatherapp.view.newLocation.NewLocationContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by avukelic on 02-Oct-18.
 */
public class NewLocationPresenter implements NewLocationContract.Presenter {

    private ApiInteractor apiInteractor;
    private NewLocationContract.View newLocationView;

    public NewLocationPresenter() {
        apiInteractor = new ApiInteractorImpl();
    }

    @Override
    public void setView(NewLocationContract.View view) {
        this.newLocationView = view;
    }

    @Override
    public void addNewLocation(String location) {
        apiInteractor.checkLocationIfExists(location, getWeatherCheckCallback(location));
    }


    private Callback<WeatherResponse> getWeatherCheckCallback(final String location) {
        return new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    newLocationView.onNewLocationAdded(location);
                } else {
                    newLocationView.showErrorNoLocationExist();
                }
            }
            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
            }
        };
    }
}
