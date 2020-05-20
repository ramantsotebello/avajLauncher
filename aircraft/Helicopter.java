package aircraft;

import java.io.PrintWriter;
import weather.*;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower _weatherTower;
    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    @Override
    public void updateConditions() {
        String weather = _weatherTower.getWeather(_coordinates);
        String type = "Helicopter#";

        if (weather.toLowerCase().equals("rain")) {
            _coordinates = new Coordinates(_coordinates.getLongitude() + 5, _coordinates.getLatitude(), _coordinates.getHeight());
                this.writeLine(type, _name, _id, "It's raining, come on, let's go. Get in the chopper, come on!");

        } else if (weather.toLowerCase().equals("fog")) {
            _coordinates = new Coordinates(_coordinates.getLongitude() + 1, _coordinates.getLatitude(), _coordinates.getHeight());
                this.writeLine(type, _name, _id, "Can't cut through Fog with Chopper bladez!");

        } else if (weather.toLowerCase().equals("sun")) {
            _coordinates = new Coordinates(_coordinates.getLongitude() + 10, _coordinates.getLatitude(), _coordinates.getHeight() + 2);
                this.writeLine(type, _name, _id, "Sunshining bright you know how I feel :)!");

        } else if (weather.toLowerCase().equals("snow")) {
            _coordinates = new Coordinates(_coordinates.getLongitude(), _coordinates.getLatitude(), _coordinates.getHeight() - 12);
                this.writeLine(type, _name, _id, "It keeps on snowing, now my blades are frosty!");

        } 
        if (_coordinates.getHeight() == 0) {
            _weatherTower.unregister(this);
            this.writeLine(type, _name, _id, " landing.");
            this.writeLine("Tower says: Helicopter#", _name, _id, " unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.writeLine("Helicopter#", _name, _id, "registered to weather tower.");
        _weatherTower = weatherTower;
        _weatherTower.register(this);
    }
}