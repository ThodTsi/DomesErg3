public class Node<T> {
    protected LargeDepositor data;
    protected Node next = null;

    Node(LargeDepositor data) {
        this.data = data;
    }

    public LargeDepositor getData() {
        // return data stored in this node
        return data;
    }

    public Node getNext() {
        // get next node
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
