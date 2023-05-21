public class PharmacyApplication {
    public static void main(String[] args) {
        dataParser data = new dataParser("./ds project data.csv");
        data.parseInitialData();
        new display(data.medicines, data.components);
    }
}
