import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int K = readInt();

        int[] daySum = new int[N + 1];
        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++){
            daySum[i] = daySum[i-1] + readInt();
            if(i>=K && max < daySum[i] - daySum[i-K]) max = daySum[i] - daySum[i-K];
        }

        System.out.println(max);
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