
import java.io.PrintStream;
import java.util.NoSuchElementException;

public class SingleList {

    protected Node head;

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
    }

    public void printList() {
        Node h = head;
        while (h != null) {
            System.out.println(h.toString());
            h = h.next;
        }
    }

    /*
     * public String removeFirst() throws NoSuchElementException {
     * try {
     * Node n = head;
     * if (head != tail) {
     * head = head.next;
     * return n.getData();
     * } else if (head == tail) {
     * head = null;
     * tail = null;
     * return n.getData();
     * }
     * } catch (NoSuchElementException e) {
     * System.out.println("nsee");
     * }
     * }
     * 
     * 
     * public void addLast(String item) {
     * Node n = new Node(item);
     * if (isEmpty()) {
     * head = n;
     * tail = n;
     * } else {
     * n.setPrev(tail);
     * tail.setNext(n);
     * tail = n;
     * }
     * }
     * 
     * public String removeLast() throws NoSuchElementException {
     * try {
     * if (!isEmpty()) {
     * return tail.getData();
     * Node n = tail;
     * tail = tail.prev;
     * tail.next = null;
     * n.prev = null;
     * } else if ((tail == head) && (tail != null)) {
     * return tail.getData();
     * head = null;
     * tail = null;
     * }
     * } catch (NoSuchElementException e) {
     * System.out.println("nsee");
     * }
     * 
     * }
     */
}
