package com.avukelic.weatherapp.presentation;

import com.avukelic.weatherapp.R;
import com.avukelic.weatherapp.interaction.DbInteractor;
import com.avukelic.weatherapp.interaction.DbInteractorImpl;
import com.avukelic.weatherapp.view.location.LocationContract;

/**
 * Created by avukelic on 02-Oct-18.
 */
public class LocationPresenter implements LocationContract.Presenter {

    private final DbInteractor dbInteractor;
    private LocationContract.View locationView;

    public LocationPresenter() {
        dbInteractor = new DbInteractorImpl();
    }

    @Override
    public void setView(LocationContract.View view) {
        this.locationView = view;
    }

    @Override
    public void getLocations() {
        locationView.showLocationsInDrawer(dbInteractor.getLocations());
    }

    @Override
    public void deleteLocationFromDb(String location, int itemId) {
        dbInteractor.deleteLocation(location);
        locationView.onLocationRemove(itemId);
    }

    @Override
    public void onDrawerItemClicked(int itemId) {
        switch (itemId) {
            case R.id.menu_add_location: {
                locationView.onNewLocationDrawerItemClicked();
                break;
            }
            default:
                locationView.onLocationDrawerItemClicked(itemId);
        }
    }
}
