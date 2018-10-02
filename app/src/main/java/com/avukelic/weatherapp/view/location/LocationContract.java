package com.avukelic.weatherapp.view.location;

import com.avukelic.weatherapp.model.LocationWrapper;

import java.util.List;

/**
 * Created by avukelic on 02-Oct-18.
 */
public interface LocationContract {

    interface View {
        void showLocationsInDrawer(List<LocationWrapper> locations);

        void onNewLocationDrawerItemClicked();

        void onLocationDrawerItemClicked(int itemId);

        void onLocationRemove(int itemId);
    }

    interface Presenter {
        void setView(LocationContract.View view);

        void getLocations();

        void deleteLocationFromDb(String location, int itemId);

        void onDrawerItemClicked(int itemId);
    }
}
