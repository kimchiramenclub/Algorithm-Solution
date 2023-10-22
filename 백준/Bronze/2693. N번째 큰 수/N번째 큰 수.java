import java.io.IOException;

class Main {
    /*
     **/
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int T = readInt();

        while (T-- > 0) {
            MaxHeap heap = new MaxHeap(10);
            for (int i = 0; i < 10; i++) heap.offer(readInt());
            heap.poll();
            heap.poll();
            sb.append(heap.poll()).append('\n');
        }

        System.out.print(sb);
    }

    static class MaxHeap {
        private int[] heap;
        private int size;

        MaxHeap(int capacity) {
            heap = new int[capacity + 1];
            size = 0;
        }

        public void offer(int val) {
            heap[++size] = val;
            swim(size);
        }

        public int poll() {
            int max = heap[1];
            swap(1, size--);
            sink(1);
            return max;
        }

        private void swim(int idx) {
            while (idx > 1 && heap[idx / 2] < heap[idx]) {
                swap(idx, idx / 2);
                idx = idx / 2;
            }
        }

        private void sink(int idx) {
            while (2 * idx <= size) {
                int j = 2 * idx;
                if (j < size && heap[j] < heap[j + 1]) j++;
                if (!(heap[idx] < heap[j])) break;
                swap(idx, j);
                idx = j;
            }
        }

        private void swap(int i, int j) {
            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }
    }

    // 입력 관련
    static int idx, size, SIZE = 1 << 13;
    static byte[] buf = new byte[SIZE];

    static int readInt() throws IOException {
        int n = 0;
        byte c;
        while ((c = read()) <= 32) ;
        do n = (n << 3) + (n << 1) + (c & 15);
        while (47 < (c = read()) && c < 58);
        return n;
    }

    static byte read() throws IOException {
        if (size == idx) {
            size = System.in.read(buf, idx = 0, SIZE);
            if (size < 0) buf[0] = -1;
        }
        return buf[idx++];
    }
}
