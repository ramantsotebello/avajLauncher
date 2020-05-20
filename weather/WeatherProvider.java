package weather;

import aircraft.*;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }


    public String getCurrentWeather(Coordinates coordinates) {
        return weather[(coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude()) % 4];
    }
}