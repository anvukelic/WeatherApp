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

    private DbInteractor dbInteractor;
    private ApiInteractor apiInteractor;
    private NewLocationContract.View newLocationView;

    public NewLocationPresenter() {
        dbInteractor = new DbInteractorImpl();
        apiInteractor = new ApiInteractorImpl();
    }

    @Override
    public void setView(NewLocationContract.View view) {
        this.newLocationView = view;
    }

    private boolean isLocationAlreadyOnList(String location) {
        return dbInteractor.isLocationAlreadyOnList(location);
    }

    @Override
    public void addNewLocation(String location) {
        if (location.isEmpty() || location.trim().length() == 0) {
            newLocationView.showErrorLocationInputFieldEmpty();
        } else if (isLocationAlreadyOnList(location)) {
            newLocationView.showErrorOnLocationAlreadyOnList();
        } else {
            apiInteractor.checkLocationIfExists(location, getWeatherCheckCallback(location));
        }
    }

    private Callback<WeatherResponse> getWeatherCheckCallback(final String location) {
        return new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    dbInteractor.saveLocation(location);
                    newLocationView.onNewLocationAdded();
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
