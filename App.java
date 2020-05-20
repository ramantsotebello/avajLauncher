
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import aircraft.*;
import weather.*;

public class App {
    public static PrintWriter writer;
    private static List<Flyable> flyables = new ArrayList<>();
    public static void main(String[] args) {

        WeatherTower weatherTower = new WeatherTower();

        try {

            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            writer = new PrintWriter(new FileWriter("simulation.txt"));
            String line = reader.readLine();
            if (line == null) {
                reader.close();
                return;
            };

            int simulations = Integer.parseInt(line.split(" ")[0]);

            if (simulations < 0) {
                System.out.println("Invalid simulations count: " + simulations);
                System.exit(1);
            }

            while ((line = reader.readLine()) != null) {

                Flyable flyable = AircraftFactory.newAircraft(
                        line.split(" ")[0], 
                        line.split(" ")[1],
                        Integer.parseInt(line.split(" ")[2]),
                        Integer.parseInt(line.split(" ")[3]),
                        Integer.parseInt(line.split(" ")[4])
                    );

                if (flyable == null) {
                    System.err.println("Error : invalid aircraft type");
                    System.exit(1);
                }

                flyables.add(flyable);
            }

            if (line == null) {
                reader.close();
            };

            for (Flyable flyable : flyables) {
                flyable.setWriter(writer);
                flyable.registerTower(weatherTower);
            }

            for (int i = 0; i < simulations; i++) {
                weatherTower.changeWeather();
            }


        }
        catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.err.println("Error : the file does not exist or is invalid.");
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}