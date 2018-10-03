package com.avukelic.weatherapp.view.location;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.avukelic.weatherapp.Consts;
import com.avukelic.weatherapp.R;
import com.avukelic.weatherapp.presentation.LocationPresenter;
import com.avukelic.weatherapp.view.newLocation.NewLocationActivity;
import com.avukelic.weatherapp.view.weather.WeatherFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationActivity extends AppCompatActivity implements LocationContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    LocationContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initUi();
        presenter = new LocationPresenter();
        presenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
            case R.id.location_search:
                startActivityForResult(new Intent(this, NewLocationActivity.class), Consts.NEW_LOCATION_REQUEST_CODE);
                return true;
            case R.id.location_map_search:
                //startActivityForResult(new Intent(this, MapActivity.class), Consts.MAP_REQUEST_CODE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUi() {
        ButterKnife.bind(this);
        initToolbar();
        createWeatherFragment("Zagreb");
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Consts.NEW_LOCATION_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                createWeatherFragment(data.getStringExtra("location"));
                getSupportActionBar().setTitle(data.getStringExtra("location").toUpperCase());
            }
        }
    }

    private void createWeatherFragment(String location) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        WeatherFragment weatherFragment = WeatherFragment.newInstance(location);
        ft.replace(R.id.weather_fragment, weatherFragment);
        ft.commit();
    }
}
