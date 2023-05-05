package Lain.Iwakura;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherAPI {

    private static final String API_KEY = "41e9d5063e0f92cba9e17f3f32f092b4";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    public static WeatherData getWeather(String city) throws Exception {
        URL url = new URL(String.format(API_URL, city, API_KEY));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        JSONObject json = new JSONObject(content.toString());
        JSONObject main = json.getJSONObject("main");
        double temp = main.getDouble("temp");
        int humidity = main.getInt("humidity");

        return new WeatherData(city, temp, humidity);
    }

    public static class WeatherData {
        private String city;
        private double temperature;
        private int humidity;

        public WeatherData(String city, double temperature, int humidity) {
            this.city = city;
            this.temperature = temperature;
            this.humidity = humidity;
        }

        public String getCity() {
            return city;
        }

        public double getTemperature() {
            return temperature;
        }

        public int getHumidity() {
            return humidity;
        }
    }
}
