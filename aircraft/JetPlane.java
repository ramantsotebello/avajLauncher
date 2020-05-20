package aircraft;

import java.io.PrintWriter;
import weather.*;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower _weatherTower;
    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    @Override
    public void updateConditions() {
        String weather = _weatherTower.getWeather(_coordinates);
        String type = "JetPlane#";

        if (weather.toLowerCase().equals("rain")) {
            _coordinates = new Coordinates(_coordinates.getLongitude(), _coordinates.getLatitude() + 5, _coordinates.getHeight());
                this.writeLine(type, _name, _id, "Let's hope this plane doesn't go down in a thunder strike!");

        } else if (weather.toLowerCase().equals("fog")) {
            _coordinates = new Coordinates(_coordinates.getLongitude(), _coordinates.getLatitude() + 1, _coordinates.getHeight());
                this.writeLine(type, _name, _id, "There is a lot of Fog, the plane might not be able to see anything, but let it land let's see what happens..");

        } else if (weather.toLowerCase().equals("sun")) {
            _coordinates = new Coordinates(_coordinates.getLongitude(), _coordinates.getLatitude() + 10, _coordinates.getHeight() + 2);
                this.writeLine(type, _name, _id, "Beautiful sunshine coming through!");

        } else if (weather.toLowerCase().equals("snow")) {
            _coordinates = new Coordinates(_coordinates.getLongitude(), _coordinates.getLatitude(), _coordinates.getHeight() - 7);
                this.writeLine(type, _name, _id, "Frozen engine means no landing, just free fall!");

        } 
        
        if (_coordinates.getHeight() == 0) {
            _weatherTower.unregister(this);
            this.writeLine(type, _name, _id, " landing");
            this.writeLine("Tower says: JetPlane#", _name, _id, " unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.writeLine("JetPlane#", _name, _id, "registered to weather tower.");
        _weatherTower = weatherTower;
        _weatherTower.register(this);
    }
}