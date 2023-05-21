import java.io.Serializable;

public class medicine implements Comparable<medicine>, Serializable {

    private final int shelfRows = 10;
    // private final int shelfColumns = 10;
    private final int rows = 20;
    private final int containers = 150;

    static int shelfColumnCounter = 0;
    static int shelfRowCounter = 0;
    static int rowCounter = 1;
    static int containerCounter = 1;

    String name;
    String manufacturer;
    String packet_size;
    String component1, component2;
    String location;

    int stock;
    double price;

    medicine(String name, String manufacturer, String packet_size, String component1, String component2, int stock,
            double price) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.packet_size = packet_size;
        this.component1 = component1;
        this.component2 = component2;
        this.stock = stock;
        this.price = price;

        location = "";
        location += (char) (65 + shelfColumnCounter);
        location += (char) (65 + shelfRowCounter);

        location += String.format("%02d", rowCounter);
        location += String.format("%03d", containerCounter++);

        if (shelfRowCounter >= shelfRows) {
            shelfRowCounter = 0;
            shelfColumnCounter++;
        }

        if (rowCounter > rows) {
            rowCounter = 1;
            shelfRowCounter++;
        }

        if (containerCounter > containers) {
            containerCounter = 1;
            rowCounter++;
        }
    }

    medicine(String name) {
        this.name = name;
    }

    public int compareTo(medicine m) {
        if (name.toLowerCase().contains(m.name.toLowerCase() + " "))
            return 0;
        return name.toLowerCase().compareTo(m.name.toLowerCase());
    }

    public String toString() {
        String s = "";
        s += "ID = " + location + "\n";
        s += "name = " + name + "\n";
        s += "manufacturer = " + manufacturer + "\n";
        s += "Packet Size = " + packet_size + "\n";
        s += "Major Component = " + component1 + "\n";
        s += "Minor Component = " + component2 + "\n";
        s += "stock = " + stock + "\n";
        s += "price = " + price + "\n";
        s += "Shelf no. = " + location.substring(0, 2) + "\n";
        s += "Row no. = " + location.substring(2, 4) + "\n";
        s += "Cabinet no. = " + location.substring(4, 7) + "\n";

        return s;
    }
}
