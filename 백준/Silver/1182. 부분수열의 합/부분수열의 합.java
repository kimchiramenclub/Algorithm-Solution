import java.io.*;
import java.util.*;

public class Main {
    /*  완전탐색
     * */

    static int[] arr;
    static int count = 0;

    static void backTrack(int sum, int S, int idx, int depth) {
        if (depth <= 0) {
            if (sum == S) count++;
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            backTrack(sum + arr[i], S, i + 1, depth - 1);
        }
    }

    static int BruteForce(int N, int S) {
        for (int n = 1; n <= N; n++) {
            backTrack(0, S, 0, n);
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        System.out.println(BruteForce(N, S));
    }
}
