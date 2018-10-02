package com.avukelic.weatherapp.interaction;

import com.avukelic.weatherapp.model.LocationWrapper;

import java.util.List;

public interface DbInteractor {

    void saveLocation(String location);

    boolean isLocationAlreadyOnList(String location);

    List<LocationWrapper> getLocations();

    void deleteLocation(String location);
}