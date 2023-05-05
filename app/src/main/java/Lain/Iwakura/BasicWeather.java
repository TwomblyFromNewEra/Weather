package Lain.Iwakura;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BasicWeather extends AppCompatActivity {
        
    private TextView cityTextView, tempTextView, humidityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityTextView = findViewById(R.id.cityTextView);
        tempTextView = findViewById(R.id.tempTextView);
        humidityTextView = findViewById(R.id.humidityTextView);

        new WeatherTask().execute("Moscow");
    }

    private class WeatherTask extends AsyncTask<String, Void, WeatherAPI.WeatherData> {

        @Override
        protected WeatherAPI.WeatherData doInBackground(String... strings) {
            try {
                return WeatherAPI.getWeather(strings[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(WeatherAPI.WeatherData weatherData) {
            if (weatherData != null) {
                cityTextView.setText(weatherData.getCity());
                tempTextView.setText((int) weatherData.getTemperature());
                humidityTextView.setText(weatherData.getHumidity());
            }
        }
    }
}
