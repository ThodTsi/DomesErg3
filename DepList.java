public class DepList {
    private Node head;

    public boolean isEmpty() {
        return head == null;
    }

    public void insert(Node n) {
        if (head == null) {
            head = n;
        } else {
            Node h = head;
            while (h != null) {
                if (h.next == null) {
                    h.setNext(n);
                }
                h = h.next;
            }
        }
    }

    public void printList() {
        if (head == null) {
            return;
        }
        Node h = head;
        while (h != null) {
            System.out.println(h.data.toString());
            h = h.next;
        }

    }

}
