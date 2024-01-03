import java.io.IOException;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = readInt();
        int excludeNum = (n * 3) % 20 < 10 ? (n * 3) / 20 : (n * 3) / 20 + 1;
        int lowDiff = 1, highDiff = 30;
        int[] levels = new int[31];

        for (int i = 0; i < n; i++) levels[readInt()]++;

        for (int i = excludeNum; i > 0; i--) {
            while (levels[lowDiff] == 0) lowDiff++;
            while (levels[highDiff] == 0) highDiff--;
            levels[lowDiff]--;
            levels[highDiff]--;
        }

        int level = (int) Math.round(
                (double) IntStream.range(1, 31)
                        .map(i -> i * levels[i])
                        .sum() / (n - excludeNum * 2)
        );
        System.out.println(level);
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