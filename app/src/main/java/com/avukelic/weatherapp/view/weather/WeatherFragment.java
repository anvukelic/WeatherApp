package com.avukelic.weatherapp.view.weather;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avukelic.weatherapp.Consts;
import com.avukelic.weatherapp.R;
import com.avukelic.weatherapp.presentation.WeatherPresenter;
import com.avukelic.weatherapp.util.TextUtils;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment implements WeatherContract.View {

    @BindView(R.id.weather_fragment_future_weather_recycler_view)
    RecyclerView futureWeatherRecyclerView;
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

    private String location;
    private WeatherContract.Presenter presenter;

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
        location = getArguments().getString("location", Consts.DEFAULT_CITY);
        presenter = new WeatherPresenter();
        presenter.setView(this);
        ButterKnife.bind(this, view);
        initUi();
    }

    private void initUi() {
        presenter.getWeather(location);
    }

    @Override
    public void showTime(String time) {
        SimpleDateFormat originalFormat = new SimpleDateFormat("MMMM, dd");
        mTime.setText(getString(R.string.weather_time, originalFormat.format(new Date())));
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
        Glide.with(getActivity().getApplicationContext()).load(Consts.IMAGE_BASE_URL + path).into(mWeatherImage);
    }

    @Override
    public void showDescription(String description) {
        mDescription.setText(TextUtils.stringFirstLetterUpper(description));
    }

    @Override
    public void showOnNetworkError(int message) {
        Toast.makeText(getActivity(), getString(message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showOnFailurError() {
        Toast.makeText(getActivity().getApplicationContext(), getString(R.string.weather_fragment_loading_failure_toast_message), Toast.LENGTH_SHORT).show();
    }
}
