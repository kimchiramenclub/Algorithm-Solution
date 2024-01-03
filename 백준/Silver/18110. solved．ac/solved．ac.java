import java.io.IOException;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        int n = readInt();
        int excludeNum = (int) Math.round((double) n * 0.15);
        int lowDiff = 1, highDiff = 30;
        int[] levels = new int[31];

        for (int i = 0; i < n; i++) levels[readInt()]++;

        for (int i = excludeNum; i > 0;) {
            while (levels[lowDiff] == 0) lowDiff++;
            if(levels[lowDiff] <= i){
                i -= levels[lowDiff];
                levels[lowDiff] = 0;
            } else{
                levels[lowDiff] -= i;
                i = 0;
            }
        }

        for (int i = excludeNum; i > 0;) {
            while (levels[highDiff] == 0) highDiff--;
            if(levels[highDiff] <= i){
                i -= levels[highDiff];
                levels[highDiff] = 0;
            } else{
                levels[highDiff] -= i;
                i = 0;
            }
        }

        int sum = 0;
        for (int i = 1; i <= 30; i++) sum += (levels[i] * i);
        System.out.println((int)Math.round((double) sum / (n - excludeNum * 2)));
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