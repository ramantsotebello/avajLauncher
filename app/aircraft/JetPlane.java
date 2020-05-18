package app.aircraft;

import app.weather.*;
import app.App;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower _weatherTower;
    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = _weatherTower.getWeather(_coordinates);
        switch (weather) {
            case "RAIN":
                _coordinates = new Coordinates(_coordinates.getLongitude(), _coordinates.getLatitude() + 5, _coordinates.getHeight());
                App.writer.println("JetPlane#" + _name + "(" + _id + "): " + "It's raining. Better watch out for lightnings");
                break;
            case "FOG":
                _coordinates = new Coordinates(_coordinates.getLongitude(), _coordinates.getLatitude() + 1, _coordinates.getHeight());
                App.writer.println("JetPlane#" + _name + "(" + _id + "): " + "The fog is killing me. Request permission to land.");
                break;
            case "SUN":
                _coordinates = new Coordinates(_coordinates.getLongitude(), _coordinates.getLatitude() + 10, _coordinates.getHeight() + 2);
                App.writer.println("JetPlane#" + _name + "(" + _id + "): " + "This is hot.");
                break;
            case "SNOW":
                _coordinates = new Coordinates(_coordinates.getLongitude(), _coordinates.getLatitude(), _coordinates.getHeight() - 7);
                App.writer.println("JetPlane#" + _name + "(" + _id + "): " + "OMG! Winter is coming!");
                break;
        }
        if (_coordinates.getHeight() == 0) {
            _weatherTower.unregister(this);
            App.writer.println("JetPlane#" + _name + "(" + _id + "): landing.");
            App.writer.println("Tower says: JetPlane#" + _name + "(" + _id + ")" + " unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        App.writer.println("JetPlane#" + _name + "(" + _id + "): " + "registered to weather tower.");
        _weatherTower = weatherTower;
        _weatherTower.register(this);
    }
}