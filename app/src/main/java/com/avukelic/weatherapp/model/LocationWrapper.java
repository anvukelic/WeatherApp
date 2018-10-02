package com.avukelic.weatherapp.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class LocationWrapper extends RealmObject {

    @Required
    @PrimaryKey
    private String location;

    public LocationWrapper() {
    }

    public LocationWrapper(String location) {
        this.location = location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

}