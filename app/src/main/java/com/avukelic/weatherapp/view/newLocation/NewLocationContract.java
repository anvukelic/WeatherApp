package com.avukelic.weatherapp.view.newLocation;

/**
 * Created by avukelic on 02-Oct-18.
 */
public interface NewLocationContract {

    interface View {
        void onNewLocationAdded();

        void showErrorNoLocationExist();

        void showErrorOnLocationAlreadyOnList();

        void showErrorLocationInputFieldEmpty();

    }

    interface Presenter {

        void setView(NewLocationContract.View view);

        void addNewLocation(String location);
    }
}
