import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int L = readInt();

        int[] holes = new int[N];
        for (int i = 0; i < N; i++) holes[i] = readInt();
        Arrays.sort(holes);

        int count = 0;
        int tapeLen = L - 1;
        int tapeReach = 0;
        for (int i = 0; i < N; i++) {
            if (tapeReach < holes[i]) {
                count++;
                tapeReach = holes[i] + L - 1;
            }
        }

        System.out.println(count);
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