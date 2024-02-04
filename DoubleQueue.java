import java.io.PrintStream;
import java.util.NoSuchElementException;

public class DoubleQueue {

    protected NodeD head;
    protected NodeD tail;
    protected int counter = 0;
    
    DoubleQueue() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(LargeDepositor item) {
        NodeD n = new NodeD(item);
        if (isEmpty()) {
            head = n;
            tail = n;
        } else {
            n.setNext(head);
            head.setPrev(n);
            head = n;
        }
        counter++;
    }

    public LargeDepositor removeFirst() throws NoSuchElementException {
        NodeD n = head;
        if (isEmpty()) {
            System.out.println("The queue is empty!");
            throw new NoSuchElementException();
        } else if ((head == tail) && (head != null)) {
            head = null;
            tail = null;
            counter--;
        } else if (head != tail) {
            head = head.next;
            head.prev = null;
            n.next = null;
            counter--;
        }
        return n.getData();
    }

    public void addLast(LargeDepositor item) {
        NodeD n = new NodeD(item);
        if (isEmpty()) {
            head = n;
            tail = n;
        } else {
            n.setPrev(tail);
            tail.setNext(n);
            tail = n;
        }
        counter++;
    }

    public LargeDepositor removeLast() throws NoSuchElementException {
        NodeD n = tail;
        if (isEmpty()) {
            System.out.println("The queue is empty!");
            throw new NoSuchElementException();
        } else if ((tail == head) && (tail != null)) {
            head = null;
            tail = null;
            counter--;
        } else if (head != tail) {
            tail = tail.prev;
            tail.next = null;
            n.prev = null;
            counter--;
        }
        return n.getData();
    }

    public LargeDepositor getFirst() {
        return head.getData();
    }

    public LargeDepositor getLast() {
        return tail.getData();
    }

    public void printQueue(PrintStream stream) {
        NodeD n = head;
        while (n != null) {
            stream.println(n.getData());
            n = n.next;
        }
    }

    public int size() {
        return counter;
    }

}
