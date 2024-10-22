public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int n;

    @SuppressWarnings("unchecked")
    MinPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
        n = 0;
    }

    void insert(Key v) {
        pq[++n] = v;
        swim(n);
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    boolean isEmpty() {
        return n == 0;
    }

    Key min() {
        if (isEmpty()) throw new RuntimeException("Priority queue underflow");
        return pq[1];
    }

    int size() {
        return n;
    }

    Key delMin() {
        if (isEmpty()) throw new RuntimeException("Priority queue underflow");
        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        return min;
    }
}