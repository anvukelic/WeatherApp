package com.avukelic.weatherapp.view.newLocation;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;

import com.avukelic.weatherapp.R;
import com.avukelic.weatherapp.presentation.NewLocationPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewLocationActivity extends AppCompatActivity implements NewLocationContract.View{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.new_location_add_location_input)
    EditText newLocationInput;

    NewLocationContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location);
        initUi();
        presenter = new NewLocationPresenter();
        presenter.setView(this);
    }

    private void initUi() {
        ButterKnife.bind(this);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.add_location);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @OnClick(R.id.new_location_add_btn)
    void addNewLocation(){
        presenter.addNewLocation(newLocationInput.getText().toString());
    }

    @Override
    public void onNewLocationAdded() {
        Toast.makeText(this, getString(R.string.location_added_success_toast_message), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void showErrorNoLocationExist() {
        newLocationInput.setError(getString(R.string.error_location_not_exist));
    }

    @Override
    public void showErrorOnLocationAlreadyOnList() {
        newLocationInput.setError(getString(R.string.error_location_already_on_list));
    }

    @Override
    public void showErrorLocationInputFieldEmpty() {
        newLocationInput.setError(getString(R.string.error_empty_input_field));
    }
}
