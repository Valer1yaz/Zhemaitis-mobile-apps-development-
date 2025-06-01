package ru.mirea.zhemaytisvs.mireaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherFragment extends Fragment {

    private TextView tvCity;
    private TextView tvCountry;
    private TextView tvTemperature;
    private TextView tvWindSpeed;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvCity = view.findViewById(R.id.tvCity);
        tvCountry = view.findViewById(R.id.tvCountry);
        tvTemperature = view.findViewById(R.id.tvTemperature);
        tvWindSpeed = view.findViewById(R.id.tvWindSpeed);

        Retrofit ipRetrofit = new Retrofit.Builder()
                .baseUrl("https://ipinfo.io/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IpInfoService ipService = ipRetrofit.create(IpInfoService.class);

        ipService.getIpInfo().enqueue(new Callback<IpInfoResponse>() {
            @Override
            public void onResponse(Call<IpInfoResponse> call, Response<IpInfoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    IpInfoResponse ipInfo = response.body();
                    tvCity.setText("Город: " + ipInfo.city);
                    tvCountry.setText("Страна: " + ipInfo.country);

                    String[] parts = ipInfo.loc.split(",");
                    double latitude = Double.parseDouble(parts[0]);
                    double longitude = Double.parseDouble(parts[1]);

                    loadWeather(latitude, longitude);
                } else {
                    tvCity.setText("Город: ошибка");
                    tvCountry.setText("Страна: ошибка");
                }
            }

            @Override
            public void onFailure(Call<IpInfoResponse> call, Throwable t) {
                tvCity.setText("Город: ошибка");
                tvCountry.setText("Страна: ошибка");
            }
        });
    }

    private void loadWeather(double latitude, double longitude) {
        Retrofit weatherRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.open-meteo.com/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OpenMeteo weatherApi = weatherRetrofit.create(OpenMeteo.class);
        weatherApi.getCurrentWeather(latitude, longitude, true).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    double temp = response.body().current_weather.temperature;
                    double wind = response.body().current_weather.windspeed;

                    tvTemperature.setText(String.format(Locale.getDefault(), "Температура: %.1f°C", temp));
                    tvWindSpeed.setText(String.format(Locale.getDefault(), "Ветер: %.1f м/с", wind));
                } else {
                    tvTemperature.setText("Температура: ошибка");
                    tvWindSpeed.setText("Ветер: ошибка");
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                tvTemperature.setText("Температура: ошибка");
                tvWindSpeed.setText("Ветер: ошибка");
            }
        });
    }
}
