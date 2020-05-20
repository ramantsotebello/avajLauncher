package aircraft;

import java.io.PrintWriter;
import aircraft.*;
import weather.*;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower _weatherTower;
    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }
    
    @Override
    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    @Override
    public void updateConditions() {
        String weather = _weatherTower.getWeather(_coordinates);
        String type = "Baloon#";

        if (weather.toLowerCase().equals("rain")) {
            _coordinates = new Coordinates(_coordinates.getLongitude(), _coordinates.getLatitude(), _coordinates.getHeight() - 5);
            this.writeLine(type, _name, _id, "Ahhh man, it's the rain again.");

        } else if (weather.toLowerCase().equals("fog")) {
            _coordinates = new Coordinates(_coordinates.getLongitude(), _coordinates.getLatitude(), _coordinates.getHeight() - 3);
            this.writeLine(type, _name, _id, "Why all the smoke, ohhh heavenly clouds...");

        } else if (weather.toLowerCase().equals("sun")) {
            _coordinates = new Coordinates(_coordinates.getLongitude() + 2, _coordinates.getLatitude(), _coordinates.getHeight() + 4);
            this.writeLine(type, _name, _id, "Yippy, it's sunny!");

        } else if (weather.toLowerCase().equals("snow")) {
            _coordinates = new Coordinates(_coordinates.getLongitude(), _coordinates.getLatitude(), _coordinates.getHeight() - 15);
            this.writeLine(type, _name, _id, "It's snowing. :(");

        } 
        if (_coordinates.getHeight() == 0) {
            this.writeLine(type, _name, _id, " landing.");
            _weatherTower.unregister(this);
            this.writeLine("Tower says: Baloon#", _name, _id, " unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.writeLine("Baloon#", _name, _id, "registered to weather tower.");
        _weatherTower = weatherTower;
        _weatherTower.register(this);
    }
}