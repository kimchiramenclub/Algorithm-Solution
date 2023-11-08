import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int N = readInt();
        Deque<int[]> deque = new ArrayDeque<>(N);
        for (int i = 1; i <= N; i++) deque.offer(new int[]{i, readInt()});

        for (int i = 1; i < N; i++) {
            int[] D = deque.poll();
            sb.append(D[0]).append(' ');

            if (D[1] > 0) {
                for (int j = 1; j < D[1]; j++) deque.offer(deque.poll());
            } else {
                for (int j = 1; j <= -D[1]; j++) deque.offerFirst(deque.pollLast());
            }
        }
        sb.append(deque.poll()[0]);

        System.out.println(sb);
    }

    // 입력 관련
    static int idx, size, SIZE = 1 << 13;
    static byte[] buf = new byte[SIZE];

    static int readInt() throws IOException {
        int sign = 1, n = 0;
        byte c;
        while ((c = read()) <= 32) ;
        if (c == '-') sign = -1;
        else n = c & 15;
        while (47 < (c = read()) && c < 58) n = (n << 3) + (n << 1) + (c & 15);
        return n * sign;
    }

    static byte read() throws IOException {
        if (size == idx) {
            size = System.in.read(buf, idx = 0, SIZE);
            if (size < 0) buf[0] = -1;
        }
        return buf[idx++];
    }
}
