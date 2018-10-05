package com.avukelic.weatherapp.view.weather;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.avukelic.weatherapp.Consts;
import com.avukelic.weatherapp.R;
import com.avukelic.weatherapp.model.WeatherResponse;
import com.avukelic.weatherapp.presentation.WeatherPresenter;
import com.avukelic.weatherapp.util.TextUtils;
import com.avukelic.weatherapp.view.adapter.WeatherForecastAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment implements WeatherContract.View, WeatherForecastAdapter.WeatherForecastClickListener {

    @BindView(R.id.weather_fragment_forecast_weather_recycler_view)
    RecyclerView mWeatherForecastRecylcerView;
    @BindView(R.id.weather_fragment_time_text_view)
    TextView mTime;
    @BindView(R.id.weather_fragment_temperature_text_view)
    TextView mTemperature;
    @BindView(R.id.weather_fragment_wind_speed_text_view)
    TextView mWindSpeed;
    @BindView(R.id.weather_fragment_pressure_text_view)
    TextView mPressure;
    @BindView(R.id.weather_fragment_humidity_text_view)
    TextView mHumidity;
    @BindView(R.id.weather_fragment_weather_image_view)
    ImageView mWeatherImage;
    @BindView(R.id.weather_fragment_description_text_view)
    TextView mDescription;
    @BindView(R.id.weather_fragment_city_text_view)
    TextView mCity;

    @BindView(R.id.weather_fragment_image_container)
    ConstraintLayout mImageContainer;
    @BindView(R.id.weather_fragment_weather_details_container)
    LinearLayout mDetailsContainer;

    @BindView(R.id.weather_fragment_progress_bar)
    ProgressBar mProgressBar;

    private WeatherForecastAdapter mAdapter;
    private String location;
    private double lat, lon;
    private WeatherContract.Presenter presenter;

    public static WeatherFragment newInstance(double lon, double lat) {
        Bundle args = new Bundle();
        args.putDouble("longitude", lon);
        args.putDouble("latitude", lat);
        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static WeatherFragment newInstance(String location) {
        Bundle args = new Bundle();
        args.putString("location", location);
        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public WeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        location = getArguments().getString("location", "");
        lon = getArguments().getDouble("longitude", 0);
        lat = getArguments().getDouble("latitude", 0);
        presenter = new WeatherPresenter();
        presenter.setView(this);
        ButterKnife.bind(this, view);
        initUi();
    }

    private void initUi() {
        if (location.trim().length() != 0) {
            presenter.getWeather(location);
            presenter.getWeatherForecast(location);
        } else {
            presenter.getWeatherByGps(lat,lon);
            presenter.getWeatherForecastByGps(lat,lon);
        }
    }

    @Override
    public void showTime(String time) {
        SimpleDateFormat df = new SimpleDateFormat("EEE, MMMM dd");
        mTime.setText(getString(R.string.weather_time, df.format(new Date(Long.parseLong(time) * 1000))));
    }

    @Override
    public void showTemperature(double temperature) {
        mTemperature.setText(getString(R.string.weather_temperature, String.valueOf((int) temperature)));
    }

    @Override
    public void showWindSpeed(double windSpeed) {
        mWindSpeed.setText(getString(R.string.weather_wind_speed, windSpeed));
    }

    @Override
    public void showPressure(double pressure) {
        mPressure.setText(getString(R.string.weather_pressure, pressure));
    }

    @Override
    public void showHumidity(int humidity) {
        mHumidity.setText(getString(R.string.weather_humidity, humidity));
    }

    @Override
    public void showWeatherIcon(String path) {
        Glide.with(this).load(Consts.IMAGE_BASE_URL + path).apply(new RequestOptions().override(300, 300)).into(mWeatherImage);
    }

    @Override
    public void showDescription(String description) {
        mDescription.setText(TextUtils.stringFirstLetterUpper(description));
    }

    @Override
    public void showOnFailureError() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.weather_fragment_loading_failure_toast_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mDetailsContainer.setVisibility(View.VISIBLE);
        mImageContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showWeatherForecast(List<WeatherResponse> weatherResponses) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new WeatherForecastAdapter(this, weatherResponses, getActivity());
        mWeatherForecastRecylcerView.setItemAnimator(new DefaultItemAnimator());
        mWeatherForecastRecylcerView.setLayoutManager(layoutManager);
        mWeatherForecastRecylcerView.setAdapter(mAdapter);
    }

    @Override
    public void showCity(String cityName) {
        mCity.setText(cityName);
    }

    @Override
    public void onWeatherForecastClick(View view, int position) {
    }
}
