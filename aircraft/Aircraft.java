package aircraft;
import java.io.PrintWriter;

public class Aircraft {
    protected long _id;
    protected String _name;
    protected Coordinates _coordinates;
    private static long idCounter = 0;
    public PrintWriter writer;

    protected Aircraft(String name, Coordinates coordinates) {
        _name = name;
        _coordinates = coordinates;
        _id = nextId();
    }

    private long nextId() {
        return idCounter += 1;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public void writeLine(String type, String name, long id, String message) {
        this.writer.println(""+ type + " " + _name + "(" + _id + "): " + message + "");
    }
}