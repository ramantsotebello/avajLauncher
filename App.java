
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import aircraft.*;
import weather.*;

public class App {
    public static PrintWriter writer;

    //Declare array list of flyables : e.g helicopter, baloon, etc
    private static List<Flyable> flyables = new ArrayList<>();
    public static void main(String[] args) {

        // Create new weather tower object
        WeatherTower weatherTower = new WeatherTower();

        //Use try catch exception handleling to run the following block of code
        try {

            // Read the file passed in as an as the first argument.
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));

            // Create a writing stream to the file  passed into the function FileWriter
            writer = new PrintWriter(new FileWriter("simulation.txt"));

            // Read one line from the file
            String line = reader.readLine();

            // Check if we've reached the end of the file, so we can close the reader.
            if (line == null) {
                reader.close();
                return;
            };

            // Create a variable that's going to hold the number of simulations the project is supposed to run.
            int simulations = Integer.parseInt(line.split(" ")[0]);

            // Check to see if the number of simulations are not less than 0, if they are print out a message and exit the application.
            if (simulations < 0) {
                System.out.println("Invalid simulations count: " + simulations);
                System.exit(1);
            }

            // Read a new line whilst there is a new line to read from the reader stream.
            while ((line = reader.readLine()) != null) {

                // Create a new aircraft using the values got from the line.
                Flyable flyable = AircraftFactory.newAircraft(
                        // Type of aircraft  
                        line.split(" ")[0], 
                        // // get Aircraft name from line
                        line.split(" ")[1],
                        // get longitude from line
                        Integer.parseInt(line.split(" ")[2]),
                        // get latitude from line 
                        Integer.parseInt(line.split(" ")[3]),
                        // get height from line
                        Integer.parseInt(line.split(" ")[4])
                    );

                // check to see if the aircraft is not invalid
                if (flyable == null) {
                    System.err.println("Error : invalid aircraft type");
                    System.exit(1);
                }

                // Add the aircraft to the array of flyables.
                flyables.add(flyable);
            }

            // check to see if the line is not equals to null, and if it is close the reader
            if (line == null) {
                reader.close();
            };

            // Iterate over the flyable array
            for (Flyable flyable : flyables) {
                // Add the writer to the flyable object
                flyable.setWriter(writer);
                // Register each flyable to the tower, pass the weather tower as a parameter
                flyable.registerTower(weatherTower);
            }

            // 
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