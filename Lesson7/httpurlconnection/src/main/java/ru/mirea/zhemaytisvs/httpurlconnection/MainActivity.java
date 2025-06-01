package ru.mirea.zhemaytisvs.httpurlconnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mirea.zhemaytisvs.httpurlconnection.databinding.ActivityMainBinding;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button.setOnClickListener(v -> {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm != null ? cm.getActiveNetworkInfo() : null;
            if (ni != null && ni.isConnected()) {
                new DownloadPageTask().execute("https://ipinfo.io/json");
            } else {
                binding.tvCity.setText("Город: Нет сети");
            }
        });
    }

    private class DownloadPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                return "";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.isEmpty()) {
                binding.tvCity.setText("Город: ошибка");
                return;
            }
            try {
                JSONObject ipJson = new JSONObject(result);
                String city = ipJson.optString("city", "--");
                String region = ipJson.optString("region", "--");
                String country = ipJson.optString("country", "--");
                String loc = ipJson.optString("loc", "0,0");
                String[] coords = loc.split(",");
                String lat = coords.length > 0 ? coords[0] : "0";
                String lon = coords.length > 1 ? coords[1] : "0";

                binding.tvCity.setText("Город: " + city);
                binding.tvRegion.setText("Регион: " + region);
                binding.tvCountry.setText("Страна: " + country);
                binding.tvLatitude.setText("Широта: " + lat);
                binding.tvLongitude.setText("Долгота: " + lon);

                String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude="
                        + lat + "&longitude=" + lon + "&current_weather=true";
                new DownloadWeatherTask().execute(weatherUrl);
            } catch (JSONException e) {
                binding.tvCity.setText("Город: ошибка разбора JSON");
            }
        }
    }

    private class DownloadWeatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadWeatherInfo(urls[0]);
            } catch (IOException e) {
                return "";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.isEmpty()) {
                binding.tvTemperature.setText("Темп.: --");
                binding.tvWindspeed.setText("Ветер: --");
                return;
            }
            try {
                JSONObject weatherJson = new JSONObject(result);
                JSONObject current = weatherJson.getJSONObject("current_weather");
                double temp = current.optDouble("temperature", Double.NaN);
                double wind = current.optDouble("windspeed", Double.NaN);

                binding.tvTemperature.setText("Темп.: " + temp + "°C");
                binding.tvWindspeed.setText("Ветер: " + wind + " м/с");
            } catch (JSONException e) {
                binding.tvTemperature.setText("Темп.: --");
                binding.tvWindspeed.setText("Ветер: --");
            }
        }
    }

    private String downloadIpInfo(String address) throws IOException {
        InputStream inputStream = null;
        String data = "";
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read;
                while ((read = inputStream.read()) != -1) {
                    bos.write(read);
                }
                bos.close();
                data = bos.toString();
            }
            connection.disconnect();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }

    private String downloadWeatherInfo(String address) throws IOException {
        InputStream inputStream = null;
        String data = "";
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read;
                while ((read = inputStream.read()) != -1) {
                    bos.write(read);
                }
                bos.close();
                data = bos.toString();
            }
            connection.disconnect();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }
}