package aircraft;
import java.io.PrintWriter;

import weather.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
    void setWriter(PrintWriter writer);
}
