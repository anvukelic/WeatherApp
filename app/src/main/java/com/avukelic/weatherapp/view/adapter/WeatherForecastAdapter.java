package com.avukelic.weatherapp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.avukelic.weatherapp.Consts;
import com.avukelic.weatherapp.R;
import com.avukelic.weatherapp.model.WeatherResponse;
import com.avukelic.weatherapp.util.ImageHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by avukelic on 04-Oct-18.
 */
public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.MyViewHolder> {

    private List<WeatherResponse> weatherResponses = new ArrayList<>();
    private WeatherForecastClickListener listener;
    private Context context;

    public WeatherForecastAdapter(WeatherForecastClickListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    public void refreshData(List<WeatherResponse> weatherList){
        weatherResponses.clear();
        weatherResponses.addAll(weatherList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.future_weather_list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        WeatherResponse weatherResponse = weatherResponses.get(position);
        Log.w("WeatherResponse", weatherResponse.toString());
        SimpleDateFormat df = new SimpleDateFormat("EEE hh:mm");
        holder.time.setText(df.format(new Date(Long.parseLong(weatherResponse.getTime()) * 1000)));
        holder.temperature.setText(context.getString(R.string.weather_temperature, String.valueOf(weatherResponse.getMain().getTemperature())));
        holder.main.setText(weatherResponse.getWeatherObject().getMain());
        holder.humidity.setText(context.getString(R.string.weather_humidity, weatherResponse.getMain().getHumidity()));
        Glide.with(context)
                .load(Consts.IMAGE_BASE_URL + ImageHelper.createWeatherIconValue(weatherResponse.getWeatherObject().getMain()))

                .into(holder.forecastImage);
    }

    public WeatherResponse getWeatherResponse(int position) {
        return weatherResponses.get(position);
    }

    @Override
    public int getItemCount() {
        return weatherResponses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.forecast_weather_list_item_image_image_view)
        ImageView forecastImage;
        @BindView(R.id.forecast_weather_list_item_time_text_view)
        TextView time;
        @BindView(R.id.forecast_weather_list_item_temperature_text_view)
        TextView temperature;
        @BindView(R.id.forecast_weather_list_item_main_text_view)
        TextView main;
        @BindView(R.id.forecast_weather_list_item_humidity_text_view)
        TextView humidity;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick
        public void onItemClick(View view) {
            if (listener != null) {
                listener.onWeatherForecastClick(view, getAdapterPosition());
            }
        }
    }

    public interface WeatherForecastClickListener {
        void onWeatherForecastClick(View view, int position);
    }
}
