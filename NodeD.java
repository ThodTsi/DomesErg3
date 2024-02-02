public class NodeD {
    private LargeDepositor data;
    protected NodeD next;
    protected NodeD prev;

    NodeD(LargeDepositor data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public LargeDepositor getData() {
        return this.data;
    }

    public void setNext(NodeD node) {
        this.next = node;
    }

    public NodeD getNext() {
        return this.next;
    }

    public void setPrev(NodeD prev) {
        this.prev = prev;
    }

    public NodeD getPrev() {
        return this.prev;
    }

}