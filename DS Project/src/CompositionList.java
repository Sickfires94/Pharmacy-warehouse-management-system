import java.io.Serializable;

public class CompositionList implements Comparable<CompositionList>, Serializable {
    String composition;
    SinglyLL<medicine> medicines;

    CompositionList(String composition) {
        this.composition = composition;
        this.medicines = new SinglyLL<>();
    }

    public int compareTo(CompositionList cl) {
        return composition.compareTo(cl.composition);
    }

    public void addMedicine(medicine m) {
        medicines.insert(m);
    }

    public SinglyLL<medicine> getMedicines() {
        return medicines;
    }

    public String toString() {
        return composition + "\n" + medicines.toString();
    }
}
