import java.io.PrintStream;
import java.util.NoSuchElementException;

public class SingleList {

    public class Node {
        protected LargeDepositor data;
        protected Node next = null;

        Node(LargeDepositor data) {
            this.data = data;
        }

        public LargeDepositor getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    protected Node head;
    protected int N = 0;

    SingleList() {
        this.head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insert(LargeDepositor item) {
        Node n = new Node(item);
        if (isEmpty()) {
            head = n;
        } else {
            n.setNext(head);
        }
        N++;
    }

    public void printList() {
        Node h = head;
        while (h != null) {
            System.out.println(h.toString());
            h = h.next;
        }
    }

    public int size() {
        return N;
    }

}
