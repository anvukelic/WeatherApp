package com.avukelic.weatherapp.view.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.avukelic.weatherapp.Consts;
import com.avukelic.weatherapp.R;
import com.avukelic.weatherapp.view.map.MapsActivity;
import com.avukelic.weatherapp.view.weather.WeatherFragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private FusedLocationProviderClient mFusedLocationClient;

    private String location;
    private double lat, lon;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        initUi();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.location_action_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.location_refresh:
                if (location.trim().length()>0) {
                    createWeatherFragment(location);
                } else {
                    createWeatherFragment(lon,lat);
                }
                return true;
            case R.id.location_search:
                searchForLocation();
                return true;
            case R.id.location_map_search:
                startActivityForResult(new Intent(this, MapsActivity.class), Consts.MAP_REQUEST_CODE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initUi() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        checkLocationPermission();
    }

    private void checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET},
                    Consts.PERMISSIONS_REQUEST_LOCATION_CODE);
        } else {
            setWeatherWithCurrentLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Consts.PERMISSIONS_REQUEST_LOCATION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setWeatherWithCurrentLocation();
                } else {
                    setDefaultWeather();
                }
        }
    }

    @SuppressLint("MissingPermission")
    private void setWeatherWithCurrentLocation() {
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            getCityNameFromGps(location.getLongitude(), location.getLatitude());
                        } else {
                            setDefaultWeather();
                        }
                    }
                });
    }

    private void getCityNameFromGps(double longitude, double latitude) {
    }

    private void setDefaultWeather() {
        createWeatherFragment(Consts.DEFAULT_CITY);
    }

    private void searchForLocation() {
        try {
            AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                    .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                    .build();
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).setFilter(typeFilter).build(this);
            startActivityForResult(intent, Consts.PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            Log.d("GooglePlayException", e.getMessage());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Consts.PLACE_AUTOCOMPLETE_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Place place = PlaceAutocomplete.getPlace(this, data);
                    createWeatherFragment(place.getName().toString());
                }
                break;
            case Consts.MAP_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    double longitude = data.getDoubleExtra("longitude", 0);
                    double latitude =  data.getDoubleExtra("latitude", 0);
                    createWeatherFragment(longitude, latitude);
                }
        }
    }

    private void createWeatherFragment(String location) {
        this.location = location;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        WeatherFragment weatherFragment = WeatherFragment.newInstance(location);
        ft.replace(R.id.weather_fragment, weatherFragment);
        ft.commit();
    }

    private void createWeatherFragment(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
        this.location = "";
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        WeatherFragment weatherFragment = WeatherFragment.newInstance(lon, lat);
        ft.replace(R.id.weather_fragment, weatherFragment);
        ft.commit();
    }
}
