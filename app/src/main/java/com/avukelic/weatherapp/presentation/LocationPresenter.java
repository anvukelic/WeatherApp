package com.avukelic.weatherapp.presentation;

import com.avukelic.weatherapp.R;
import com.avukelic.weatherapp.interaction.ApiInteractor;
import com.avukelic.weatherapp.interaction.ApiInteractorImpl;
import com.avukelic.weatherapp.interaction.DbInteractor;
import com.avukelic.weatherapp.interaction.DbInteractorImpl;
import com.avukelic.weatherapp.view.location.LocationContract;

/**
 * Created by avukelic on 02-Oct-18.
 */
public class LocationPresenter implements LocationContract.Presenter {

    private LocationContract.View locationView;

    public LocationPresenter() {
    }

    @Override
    public void setView(LocationContract.View view) {
        this.locationView = view;
    }

}
