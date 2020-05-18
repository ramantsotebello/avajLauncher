package app.aircraft;

import app.weather.*;
import app.aircraft.*;
import app.App;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower _weatherTower;
    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }
    

    @Override
    public void updateConditions() {
        String weather = _weatherTower.getWeather(_coordinates);
        switch (weather) {
            case "RAIN":
                _coordinates = new Coordinates(_coordinates.getLongitude(), _coordinates.getLatitude(), _coordinates.getHeight() - 5);
                App.writer.println("Baloon#" + _name + "(" + _id + "): " + "Ahhh man, it's the rain again.");
                break;
            case "FOG":
                _coordinates = new Coordinates(_coordinates.getLongitude(), _coordinates.getLatitude(), _coordinates.getHeight() - 3);
                App.writer.println("Baloon#" + _name + "(" + _id + "): " + "Why all the smoke, ohhh heavenly clouds...");
                break;
            case "SUN":
                _coordinates = new Coordinates(_coordinates.getLongitude() + 2, _coordinates.getLatitude(), _coordinates.getHeight() + 4);
                App.writer.println("Baloon#" + _name + "(" + _id + "): " + "Yippy, it's sunny!");
                break;
            case "SNOW":
                _coordinates = new Coordinates(_coordinates.getLongitude(), _coordinates.getLatitude(), _coordinates.getHeight() - 15);
                App.writer.println("Baloon#" + _name + "(" + _id + "): " + "It's snowing. :(");
                break;
        }
        if (_coordinates.getHeight() == 0) {
            App.writer.println("Baloon#" + _name + "(" + _id + "): landing.");
            App.writer.println("Tower says: Baloon#" + _name + "(" + _id + ")" + " unregistered from weather tower.");
            _weatherTower.unregister(this);
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        App.writer.println("Baloon#" + _name + "(" + _id + "): " + "registered to weather tower.");
        _weatherTower = weatherTower;
        _weatherTower.register(this);
    }
}