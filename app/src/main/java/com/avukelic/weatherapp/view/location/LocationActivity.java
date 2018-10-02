package com.avukelic.weatherapp.view.location;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;

import com.avukelic.weatherapp.R;
import com.avukelic.weatherapp.model.LocationWrapper;
import com.avukelic.weatherapp.presentation.LocationPresenter;
import com.avukelic.weatherapp.util.AlertDialogUtils;
import com.avukelic.weatherapp.view.newLocation.NewLocationActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationActivity extends AppCompatActivity implements LocationContract.View, AlertDialogUtils.OnAlertDialogButtonClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.location_drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.location_navigation_view)
    NavigationView navigationView;

    private Menu drawerMenu;
    private SubMenu drawerSubMenu;

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
        presenter.getLocations();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (android.R.id.home):
                drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUi() {
        ButterKnife.bind(this);
        initToolbar();
        initNavigationDrawer();
        initDrawerLocationSubMenu();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("WeatherApp");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        toolbar.setNavigationIcon(R.drawable.ic_navigation_drawer);
    }

    private void initNavigationDrawer() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //presenter.onDrawerItemClicked(item.getItemId());
                return false;
            }
        });
    }

    private void initDrawerLocationSubMenu() {
        drawerMenu = navigationView.getMenu();
        drawerSubMenu = drawerMenu.addSubMenu(R.string.sub_menu_title);
    }

    @Override
    public void showLocationsInDrawer(List<LocationWrapper> locations) {
        drawerSubMenu.clear();
        if (locations.size() > 0) {
            for (int i = 0; i < locations.size(); i++) {
                drawerSubMenu.add(Menu.NONE, i, Menu.NONE, locations.get(i).getLocation());
            }
        }
    }

    @Override
    public void onNewLocationDrawerItemClicked() {
        startActivity(new Intent(this, NewLocationActivity.class));
        drawerLayout.closeDrawers();
    }

    @Override
    public void onLocationDrawerItemClicked(int itemId) {
        AlertDialogUtils.askForDeleteAlertDialog(this, drawerSubMenu.getItem(itemId).toString(), this,itemId);
    }

    @Override
    public void onLocationRemove(int itemId) {
        drawerSubMenu.removeItem(itemId);
    }

    @Override
    public void onAlertDialogConfirm(String location, int itemId) {
        presenter.deleteLocationFromDb(location, itemId);
    }
}
