import java.io.IOException;
import java.util.Arrays;

public class Main {
    /*  이분탐색
     * */

    static final int max = Integer.MAX_VALUE;
    
    static int binarySearch(int K, int N, int[] cables) {
        int lo = 1;
        int hi = cables[K - 1];
        int mid;

        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;    // overflow 방지

            long sum = 0;
            for (int i = K - 1; i >= 0; i--) {
                sum += (cables[i] / mid);
                if (sum >= N) break;
            }

            if (sum >= N && lo != max) lo = mid + 1;
            else if(sum < N) hi = mid - 1;
            else break;
        }
        return hi;
    }


    public static void main(String[] args) throws IOException {
        int K = readInt();  // 소유 랜선 갯수
        int N = readInt();  // 필요한 랜선 갯수

        int[] cables = new int[K];
        for (int i = 0; i < K; i++) cables[i] = readInt();
        Arrays.sort(cables);

        System.out.print(binarySearch(K, N, cables));
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