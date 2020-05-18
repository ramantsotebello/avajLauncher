package app.aircraft;

import app.weather.*;
import app.App;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower _weatherTower;
    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = _weatherTower.getWeather(_coordinates);
        switch (weather) {
            case "RAIN":
                _coordinates = new Coordinates(_coordinates.getLongitude() + 5, _coordinates.getLatitude(), _coordinates.getHeight());
                App.writer.println("Helicopter#" + _name + "(" + _id + "): " + "It's raining.");
                break;
            case "FOG":
                _coordinates = new Coordinates(_coordinates.getLongitude() + 1, _coordinates.getLatitude(), _coordinates.getHeight());
                App.writer.println("Helicopter#" + _name + "(" + _id + "): " + "I can't see shit man, what's going on!!.");
                break;
            case "SUN":
                _coordinates = new Coordinates(_coordinates.getLongitude() + 10, _coordinates.getLatitude(), _coordinates.getHeight() + 2);
                App.writer.println("Helicopter#" + _name + "(" + _id + "): " + "This is hot.");
                break;
            case "SNOW":
                _coordinates = new Coordinates(_coordinates.getLongitude(), _coordinates.getLatitude(), _coordinates.getHeight() - 12);
                App.writer.println("Helicopter#" + _name + "(" + _id + "): " + "My rotor is going to freeze!");
                break;
        }
        if (_coordinates.getHeight() == 0) {
            _weatherTower.unregister(this);
            App.writer.println("Helicopter#" + _name + "(" + _id + "): landing.");
            App.writer.println("Tower says: Helicopter#" + _name + "(" + _id + ")" + " unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        App.writer.println("Helicopter#" + _name + "(" + _id + "): " + "registered to weather tower.");
        _weatherTower = weatherTower;
        _weatherTower.register(this);
    }
}