import java.io.Serializable;

public class SinglyLL<T extends Comparable<T>> implements Serializable {
    Node<T> head;
    Node<T> tail;

    void insert(T data) {
        Node<T> n = new Node<T>(data);

        if (isEmpty()) {
            head = n;
            tail = n;
            return;
        }

        tail.next = n;
        tail = n;
    }

    void delete(T data) {
        Node<T> temp = head;
        Node<T> prev = head;

        while (temp != null && data.compareTo(temp.data) != 0) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null)
            return;

        if (temp == head) {
            head = head.next;
            return;
        }

        prev.next = temp.next;
    }

    T search(T data) {
        Node<T> temp = head;

        while (temp != null && data.compareTo(temp.data) != 0) {
            temp = temp.next;
        }
        if (temp == null)
            return null;

        return temp.data;
    }

    boolean isEmpty() {
        return head == null;
    }

    int size() {
        Node<T> temp = head;
        int counter = 0;
        while (temp != null) {
            temp = temp.next;
            counter++;
        }
        return counter;
    }

    T getIndex(int i) {
        int counter = 0;
        Node<T> temp = head;
        while (counter < i)
            temp = temp.next;

        return temp.data;
    }

    public String toString() {
        Node<T> temp = head;
        String str = " ";
        while (temp != null) {
            str += temp.data.toString() + " ";
            str += "\n ********************* \n";
            temp = temp.next;
        }
        str += "\n";
        return str;
    }

}

class Node<T extends Comparable<T>> implements Serializable {

    T data;

    Node<T> next;

    Node(T data) {
        this.data = data;
    }
}
