public class Node{
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
