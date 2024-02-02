public class PQ {
    protected LargeDepositor[] heap;
    protected int size;
    public static final int def_cap = 5;
    public static final int auto_grow = 5;

    public PQ() {
        this.heap = new LargeDepositor[def_cap + 1];
        this.size = 0;
    }

    protected void swim(int i) {
        if (i == 0)
            return;

        while ((i > 1) & (i / 2 < i)) {
            if (heap[i].compareTo(heap[i / 2]) == -1) {
                LargeDepositor temp = heap[i / 2];
                heap[i / 2] = heap[i];
                heap[i] = temp;
            }
            i /= 2;
        }
    }

    protected boolean isEmpty() {
        return (heap[1] == null);
    }

    protected int size() {
        return this.size;
    }

    protected void insert(LargeDepositor c) {
        double lim = Math.ceil(0.75 * size);
        if (heap[(int) lim] != null) {
            resize();
        }
        size++;
        heap[size] = c;
        swim(size);

    }

    protected void resize() {
        LargeDepositor[] newHeap = new LargeDepositor[heap.length + auto_grow];
        System.arraycopy(heap, 1, newHeap, 1, size);

        heap = newHeap;
    }

    protected void exchange(int i, int j) {
        LargeDepositor temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    protected LargeDepositor remove(int k) throws NullPointerException {
        try {
            int i = 1;
            int index = 0;
            boolean stop = false;
            LargeDepositor temp = null;
            while (i < heap.length & stop == false) {
                if (heap[i].key()== k) {
                    stop = true;
                    index = i;
                }
                i++;
            }
            LargeDepositor[] c = new LargeDepositor[heap.length];
            System.arraycopy(heap, 1, c, 1, index);
            System.arraycopy(heap, index + 1, c, index, heap.length - index - 1);
            temp = heap[index];
            heap = c;
            size--;
            swim(size);
            return temp;
        } catch (NullPointerException e) {
            System.out.println("There is no large depositor with this taxed income");
            return null;
        }
    }

    protected LargeDepositor min() {
        return heap[1];
    }

    protected LargeDepositor getMin() {
        LargeDepositor temp = heap[1];
        remove(heap[1].key());
        return temp;
    }
}
