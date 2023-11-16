import java.io.*;
import java.util.StringTokenizer;

public class Main {
    /*      이분탐색
            - Z >= 99 이면, 절대 변할 수 없음.
            - Z = [(Y+k)/(X+k)] * 100.
                ㄴ Y가 작을 수록, k의 영향이 크고, Y가 클수록 k의 영향이 작음. (대체적으로)
                ㄴ X = 1,000,000,000 , Y = 980,000,000일 경우가 가장 큰 판수가 필요할 것임.
                ㄴ 이때, 1,000,000,000 판을 더 해야 Z = 99가 되므로, max = 2,000,000,000

    * */

    static final long max = 2_000_000_000L;

    static long binarySearch(long X, long Y, long Z) {
        if (Z >= 99) return -1;

        long left = X;
        long right = max;
        long mid;
        long diff;

        while (left < right) {
            mid = (left + right) / 2;
            diff = mid - X;

            if ((Y + diff) * 100 / mid == Z) left = mid + 1;
            else right = mid;
        }
        return left - X;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());
        long Z = Y * 100L / X;

        System.out.println(binarySearch(X, Y, Z));
    }
}