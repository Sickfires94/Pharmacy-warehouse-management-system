import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class dataParser {

    private final int stock_randomizer = 100;
    static int counter = 0;
    String dataPath;
    BST<CompositionList> components;
    BST<medicine> medicines;

    dataParser(String dataPath) {
        this.dataPath = dataPath;
        components = new BST<>();
        medicines = new BST<>();
    }

    void parseInitialData() {

        try {
            FileReader f = new FileReader(dataPath);
            BufferedReader br = new BufferedReader(f);

            int counter = 0;
            String readLine = br.readLine();
            counter++;

            while ((readLine = br.readLine()) != null) {
                if (counter == 0) {

                    continue;
                }
                String[] line = readLine.split(",");
                if (line.length < 5) {
                    counter++;
                    continue;
                }
                String name = line[0];
                double price = Double.parseDouble(line[1]);
                String manufacturer_name = line[2];
                String packetSize = line[3];
                String composistion1 = line[4];
                String composition2 = "NONE";
                if (line.length != 5) {
                    composition2 = line[5];
                }

                int stock = (int) (Math.random() * stock_randomizer);

                medicine m = new medicine(name, manufacturer_name, packetSize, composistion1,
                        composition2, stock,
                        price);

                medicines.insert(m);

                addRecomendations(composistion1, composition2, m);

                counter++;
                if (counter % 10000 == 0)
                    System.out.println(counter);
            }
            br.close();
            f.close();
        } catch (IOException e) {
            System.out.println("Reading error");
            e.printStackTrace();
        }
    }

    void addRecomendations(String c1, String c2, medicine m) {
        CompositionList cl = components.find(new CompositionList(c1 + " " + c2));
        if (cl == null) {
            cl = new CompositionList(c1 + " " + c2);
            components.insert(cl);
        }

        cl.addMedicine(m);
    }

}
