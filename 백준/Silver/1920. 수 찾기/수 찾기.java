import java.io.IOException;
import java.util.Arrays;

public class Main {
    /*  브루트포스

        수정 : mid 값이 (low+high)/2로 바로 계산해버리면 오버플러우 남.
     * */

    static byte findInt(int[] num, int low, int high, int X) {
        while (low <= high) {
            int mid = (low + high) >> 1;

            if (X == num[mid]) return 1;
            else if (X < num[mid]) high = mid - 1;
            else low = mid + 1;
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int N = readInt();
        int[] num = new int[N];
        for (int i = 0; i < N; i++) num[i] = readInt();

        Arrays.sort(num);

        int M = readInt();
        for (int i = 0; i < M; i++) sb.append(findInt(num, 0, N - 1, readInt())).append("\n");

        System.out.println(sb);
    }

    static int readInt() throws IOException {
        int n = 0, sign = 1, c;
        if ((c = System.in.read()) == '-') sign = -1;
        else n = c & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n * sign;
    }
}
